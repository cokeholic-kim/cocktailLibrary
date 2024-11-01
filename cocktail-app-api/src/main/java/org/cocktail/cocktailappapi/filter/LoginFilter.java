package org.cocktail.cocktailappapi.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cocktail.cocktailappapi.domain.user.controller.model.CustomUserDetails;
import org.cocktail.cocktailappapi.jwt.JwtUtil;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final String domainName;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        // JSON 데이터 읽기
        String userName, password;
        try {
            // 요청 본문에서 JSON 데이터 읽기
            Map<String, String> credentials = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            userName = credentials.get("username");
            password = credentials.get("password");
        } catch (IOException e) {
            // 폼 데이터 읽기
            userName = obtainUsername(request);
            password = obtainPassword(request);
        }

        log.info("username {}", userName);
        log.info("password {}", password);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName,
                password, null);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        //인증 성공
        CustomUserDetails customUserDetails = (CustomUserDetails) authResult.getPrincipal();

        String username = customUserDetails.getUsername();
        String role = getRole(customUserDetails);

        //jwt 발급
        String token = jwtUtil.createJwt(username, role, 60 * 60 * 1000L);
        log.info("domainName = {}",domainName);
        ResponseCookie cookie = ResponseCookie.from("Authorization",token)
                .path("/")
                .maxAge(60*60)
                .domain("." + domainName)
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }

    private String getRole(CustomUserDetails customUserDetails) {
        return customUserDetails.getAuthorities().iterator().next().getAuthority();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        //인증 실패
        response.setStatus(401);
    }
}

