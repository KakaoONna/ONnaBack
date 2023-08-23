package com.onna.onnaback.global.oauth.handler;

import com.onna.onnaback.domain.member.adapter.out.persistence.MemberRepository;
import com.onna.onnaback.domain.member.domain.Role;
import com.onna.onnaback.global.jwt.JwtService;
import com.onna.onnaback.global.oauth.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final MemberRepository memberRepository;
    @Value("${jwt.url}")
    private String redirectUrl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 Login 성공!");
        try {
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

            // User의 Role이 GUEST일 경우 처음 요청한 회원이므로 회원가입 페이지로 리다이렉트
            if(oAuth2User.getRole() == Role.GUEST) {
//                String accessToken = jwtService.createAccessToken(oAuth2User.getEmail());
//                response.addHeader(jwtService.getAccessHeader(), "Bearer " + accessToken);
//                response.sendRedirect("oauth2/sign-up"); // 프론트의 회원가입 추가 정보 입력 폼으로 리다이렉트
//
//                jwtService.sendAccessAndRefreshToken(response, accessToken, null);
                String targetUrl = UriComponentsBuilder.fromUriString(redirectUrl)
                        .queryParam("email", (String) oAuth2User.getAttribute("email"))
                        .build()
                        .encode(StandardCharsets.UTF_8)
                        .toUriString();
                // 회원가입 페이지로 리다이렉트 시킨다.
                getRedirectStrategy().sendRedirect(request, response, targetUrl);

            } else {
//                loginSuccess(response, oAuth2User); // 로그인에 성공한 경우 access, refresh 토큰 생성
                // accessToken을 쿼리스트링에 담는 url을 만들어준다.
                String targetUrl = UriComponentsBuilder.fromUriString(redirectUrl)
                        .queryParam("accessToken", jwtService.createAccessToken(oAuth2User.getEmail()))
                        .build()
                        .encode(StandardCharsets.UTF_8)
                        .toUriString();
                log.info("redirect 준비");
                // 로그인 확인 페이지로 리다이렉트 시킨다.
                getRedirectStrategy().sendRedirect(request, response, targetUrl);
            }
        } catch (Exception e) {
            throw e;
        }

    }



    private void loginSuccess(HttpServletResponse response, CustomOAuth2User oAuth2User) throws IOException {
        String accessToken = jwtService.createAccessToken(oAuth2User.getEmail());
        String refreshToken = jwtService.createRefreshToken();
        response.addHeader(jwtService.getAccessHeader(), "Bearer " + accessToken);
        response.addHeader(jwtService.getRefreshHeader(), "Bearer " + refreshToken);

        jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);
        jwtService.updateRefreshToken(oAuth2User.getEmail(), refreshToken);
    }
}
