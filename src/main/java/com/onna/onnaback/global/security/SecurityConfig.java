package com.onna.onnaback.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onna.onnaback.domain.member.adapter.out.persistence.MemberRepository;

import com.onna.onnaback.domain.member.application.service.JwtService;
import com.onna.onnaback.domain.member.application.service.OAuthLoginService;
import com.onna.onnaback.global.jwt.filter.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.Filter;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final OAuthLoginService oAuthLoginService;
    private final JwtService jwtService;
    private final MemberRepository memberRepository;
    private final ObjectMapper objectMapper;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // [PART 1]
                .formLogin().disable() // FormLogin 사용 X
                .httpBasic().disable() // httpBasic 사용 X
                .csrf().disable() // csrf 보안 사용 X
                .cors().configurationSource(corsConfigurationSource())
                .and()
                // 세션 사용하지 않으므로 STATELESS로 설정
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // [PART 2]
                //== URL별 권한 관리 옵션 ==//
                .authorizeRequests()
                .antMatchers("/swagger-ui/**","/v3/api-docs", "/swagger-resources/**","/api/auth/**","/").permitAll()
                .anyRequest().authenticated()// 위의 경로 이외에는 모두 인증된 사용자만 접근 가능
                .and()
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        JwtAuthenticationFilter jwtAuthenticationFilter=new JwtAuthenticationFilter(jwtService,memberRepository);
        return jwtAuthenticationFilter;
    }

    // CORS 허용 적용
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:3000","https://kakao-onna.vercel.app"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }



}