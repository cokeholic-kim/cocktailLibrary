package org.cocktail.cocktailappapi.oauth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cocktail.cocktailappapi.jwt.JwtUtil;
import org.cocktail.cocktailappapi.oauth.dto.CustomOauth2User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    @Value("${cors.allowed.origins}")
    private String corsAllowed;
    @Value("${domain.name}")
    private String domainName;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        CustomOauth2User customUserDetails = (CustomOauth2User) authentication.getPrincipal();
        String userName = customUserDetails.getName();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        String token = jwtUtil.createJwt(userName, role, 60 * 60 * 1000L);

        ResponseCookie cookie = ResponseCookie.from("Authorization",token)
                .path("/")
                .maxAge(60*60)
                .domain("."+domainName)
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
        response.sendRedirect(corsAllowed);
    }
}
