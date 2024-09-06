package org.cocktail.cocktailappapi.oauth.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cocktail.cocktailappapi.domain.user.validate.ErrorCodeUser;
import org.cocktail.cocktailappapi.oauth.dto.CustomOauth2User;
import org.cocktail.cocktailappapi.oauth.dto.GoogleResponse;
import org.cocktail.cocktailappapi.oauth.dto.NaverResponse;
import org.cocktail.cocktailappapi.oauth.dto.Oauth2Response;
import org.cocktail.cocktailappapi.oauth.dto.UserDto;
import org.cocktail.common.exception.ApiException;
import org.cocktail.db.user.UserEntity;
import org.cocktail.db.user.UserRepository;
import org.cocktail.db.user.enums.LoginMethod;
import org.cocktail.db.user.enums.UserRole;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("user info {}",oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        Oauth2Response oauth2Response = null;

        if(registrationId.equals("naver")){
            oauth2Response = new NaverResponse(oAuth2User.getAttributes());
        }else if(registrationId.equals("google")){
            oauth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else {
            return null;
        }

        String username = oauth2Response.getProvider() + " " + oauth2Response.getProviderId();
        String userEmail = oauth2Response.getEmail();
//        Optional<UserEntity> byEmail = userRepository.findByEmail(userEmail);
        Optional<UserEntity> byNickName = userRepository.findByNickName(username);
        //db 로직 작성
        /*
        * 유저이름으로 데이터를조회해서 데이터가있으면 로그인으로 처리
        * 유저정보가없으면 해당 데이터로 유저엔티티를 만든후 저장 .
        * 유저정보가있는경우 해당데이터로 userDto를 생성해서 리턴  // 이메일과 이름 부분이 업데이트된경우를 생각해 업데이트도 해준다.
        * */

        /**
         * 처음 간편 로그인을 시도하는 경우: 사용자의 이메일이 기존 사용자와 겹치면, 가입된 사용자로 간주하고 로그인 처리합니다.
         * 이미 가입된 이메일로 간편 로그인을 시도하는 경우: 기존 사용자로 로그인 처리하고, 추가적인 안내 메시지를 표시할 수 있습니다.
         */

        if(byNickName.isEmpty()){
            UserEntity userEntity = UserEntity.builder()
                    .nickName(username)
                    .email(oauth2Response.getEmail())
                    .role(UserRole.ROLE_USER)
                    .loginMethod(LoginMethod.valueOf(oauth2Response.getProvider().toUpperCase()))
                    .build();

            userRepository.save(userEntity);

            UserDto roleUser = UserDto.builder()
                    .username(username)
                    .name(oauth2Response.getName())
                    .role("USER")
                    .build();

            return new CustomOauth2User(roleUser);
        } else {
            UserEntity userEntity = byNickName.orElseThrow(() -> new ApiException(ErrorCodeUser.JOIN_ERROR));
            userEntity.setEmail(oauth2Response.getEmail());
            userEntity.setNickName(username);

            userRepository.save(userEntity);

            UserDto roleUser = UserDto.builder()
                    .username(userEntity.getNickName())
                    .name(oauth2Response.getName())
                    .role(String.valueOf(userEntity.getRole()))
                    .build();

            return new CustomOauth2User(roleUser);
        }
    }
}
