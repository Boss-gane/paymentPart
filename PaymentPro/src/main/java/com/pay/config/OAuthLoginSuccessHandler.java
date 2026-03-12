package com.pay.config;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.pay.entity.User;
import com.pay.repo.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;

    public OAuthLoginSuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        OAuth2User user = (OAuth2User) authentication.getPrincipal();

        String email = user.getAttribute("email");

        Optional<User> dbUser = userRepository.findByEmail(email);

        if (dbUser.isEmpty()) {
            response.sendRedirect("/access-denied");
            return;
        }

        response.sendRedirect("/home");
    }
}