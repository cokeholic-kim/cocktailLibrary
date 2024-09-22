package org.cocktail.cocktailappapi.oauth.service;

import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cocktail.cocktailappapi.oauth.dto.CustomOauth2User;
import org.cocktail.cocktailappapi.oauth.dto.GoogleResponse;
import org.cocktail.cocktailappapi.oauth.dto.NaverResponse;
import org.cocktail.cocktailappapi.oauth.dto.Oauth2Response;
import org.cocktail.cocktailappapi.oauth.dto.UserDto;
import org.cocktail.db.ouathuser.Oauth2UserEntity;
import org.cocktail.db.ouathuser.Oauth2UserRepository;
import org.cocktail.db.user.UserEntity;
import org.cocktail.db.user.UserRepository;
import org.cocktail.db.user.enums.LoginMethod;
import org.cocktail.db.user.enums.UserRole;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final Oauth2UserRepository oauth2UserRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        Oauth2Response oauth2Response;

        //간편로그인별 리스폰스 객체 만들기
        if (registrationId.equals("naver")) {
            oauth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else if (registrationId.equals("google")) {
            oauth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else {
            throw new OAuth2AuthenticationException(new OAuth2Error("unsupported_registration_id"),
                    "Unsupported registration ID");
        }

        String username = oauth2Response.getName();
        String userEmail = oauth2Response.getEmail();

        /*

        * 1. oauth_user 테이블에서 이메일로 조회
        * 데이터가 있는경우 oauth_user 에서 연결된 유저테이블을 조회
        * oauth_uesr 테이블에서 조회된데이터가없는경우
            * 이메일로 user테이블을 조회
             -> 유저가 있는경우
                * 현재 response로 oauth_user 를저장하고 ,
                * 해당 user와 간편로그인 user를연결
            * 없는경우 userEntity를 등록
            * oauth_user 의 user 필드에 해당 userEntity를 넣어서 저장.
        * oauth2_user 테이블에서 조회된데이터가 있는경우
            * 조회된데이터의 user필드를 찾아서 리턴
            * 해당 user 를 userDto로 컨버팅해서 Oauth2Response로 보내준다.


        예외케이스 : oauth_user에도 같은 이메일이 들어갈수있다. ->  이메일과 , 로그인하는 도메인을 같이 저장
        */

        Optional<Oauth2UserEntity> byEmailOauthUser = oauth2UserRepository.findByEmailAndDomain(userEmail,registrationId);

        if (byEmailOauthUser.isEmpty()) { // 처음으로 간편로그인을하는경우 , oauth_user에 데이터가 없는경우
            // oauth_user 이메일을 가진 uesr가있는지 조회
            Optional<UserEntity> byEmail = userRepository.findByEmail(userEmail);

            // 있다면 해당 유저데이터를 oauth_user에 등록 없다면 userEntity를 생성해서 등록
            UserEntity userEntity = byEmail.orElse(UserEntity.builder()
                    .email(userEmail)
                    .nickName(username)
                    .role(UserRole.ROLE_USER)
                    .loginMethod(LoginMethod.valueOf(registrationId))
                    .build());

            // oauth_user에 로그인한 유저를 저장
            Oauth2UserEntity oauthUserEntity = Oauth2UserEntity.builder()
                    .user(userEntity)
                    .email(userEmail)
                    .domain(registrationId)
                    .build();

            oauth2UserRepository.save(oauthUserEntity);

            UserDto roleUser = UserDto.builder()
                    .username(username)
                    .name(oauth2Response.getName())
                    .email(userEmail)
                    .role("USER")
                    .build();

            return new CustomOauth2User(roleUser);
        } else {
            //간편로그인 계정이 등록되어있는경우 , 연결된 유저를 찾아와서 roleUser를 생성해서 리턴.
            Oauth2UserEntity oauth2UserEntity = byEmailOauthUser.get();
            UserEntity userEntity = oauth2UserEntity.getUser();

            UserDto roleUser = UserDto.builder()
                    .username(userEntity.getNickName())
                    .name(oauth2Response.getName())
                    .email(userEmail)
                    .role(String.valueOf(userEntity.getRole()))
                    .build();

            return new CustomOauth2User(roleUser);
        }
    }
}
