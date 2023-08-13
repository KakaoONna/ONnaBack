package com.onna.onnaback.global.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;

// 의존성을 느슨하게 하기 위한 수동 빈 등록
@Configuration
public class OAuthConfig {
    @Bean
    public DefaultOAuth2UserService defaultOAuth2UserService() {
        return new DefaultOAuth2UserService();
    }
}