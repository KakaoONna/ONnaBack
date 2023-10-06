package com.onna.onnaback.domain.member.application.service;

import com.onna.onnaback.domain.member.adapter.out.external.response.kakao.KakaoInfoResponse;
import com.onna.onnaback.domain.member.adapter.out.external.response.kakao.KakaoTokens;
import com.onna.onnaback.domain.member.application.port.in.OAuthUseCase;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;



@Service
@RequiredArgsConstructor
@Getter
@Slf4j
@Transactional(readOnly = true)
public class OAuthService implements OAuthUseCase {

    private static final String GRANT_TYPE = "authorization_code";


    @Value("${oauth.kakao.url.auth}")
    private String authUrl;

    @Value("${oauth.kakao.url.api}")
    private String apiUrl;

    @Value("${oauth.kakao.client-id}")
    private String clientId;

    @Value("${oauth.kakao.url.redirect-uri}")
    private String redirectUri;

    @Value("${oauth.kakao.url.redirect-local}")
    private String redirectLocalUri;


    private final RestTemplate restTemplate;




    @Override
    public String requestAccessToken(String authorizationCode) {

        String tokenUri = authUrl + "/oauth/token";

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.add("Accept", "application/json");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code",authorizationCode);
        body.add("grant_type", GRANT_TYPE);
        body.add("client_id", clientId);
        body.add("redirect_uri",redirectUri);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body,httpHeaders);

        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        KakaoTokens response = restTemplate.postForObject(tokenUri, request, KakaoTokens.class);
        assert response != null;
        return response.getAccessToken();

    }

    @Override
    public String requestLocalAccessToken(String authorizationCode) {

        String tokenUri = authUrl + "/oauth/token";

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.add("Accept", "application/json");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code",authorizationCode);
        body.add("grant_type", GRANT_TYPE);
        body.add("client_id", clientId);
        body.add("redirect_uri",redirectLocalUri);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body,httpHeaders);

        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        KakaoTokens response = restTemplate.postForObject(tokenUri, request, KakaoTokens.class);
        assert response != null;
        return response.getAccessToken();

    }


    @Override
    public KakaoInfoResponse requestOauthInfo(String accessToken) {
        String url = apiUrl + "/v2/user/me";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("property_keys", "[\"kakao_account.email\", \"kakao_account.profile\",\"kakao_account.gender\",\"kakao_account.age_range\"," +
                "\"kakao_account.name\",\"kakao_account.birthday\"]");

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);

        return restTemplate.postForObject(url, request, KakaoInfoResponse.class);
    }


}