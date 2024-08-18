package org.cocktail.cocktailappapi.oauth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
public class CustomFailureHandler implements AuthenticationFailureHandler {
    @Value("${cors.allowed.origins}")
    private String corsAllowed;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = exception.getMessage();
        response.sendRedirect(corsAllowed + "/login?error=" +  URLEncoder.encode(errorMessage + "다시 로그인해 주세요", "UTF-8"));
    }

}
