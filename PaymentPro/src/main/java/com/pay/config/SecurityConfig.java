package com.pay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
	                                                   OAuthLoginSuccessHandler successHandler) throws Exception {

	        httpSecurity
	                .authorizeHttpRequests(req -> req
	                        .anyRequest().authenticated()
	                )
	                .formLogin(Customizer.withDefaults())
	                .oauth2Login(oauth -> oauth
	                        .successHandler(successHandler)
	                );

	        return httpSecurity.build();
	    }
	
	
	
}
