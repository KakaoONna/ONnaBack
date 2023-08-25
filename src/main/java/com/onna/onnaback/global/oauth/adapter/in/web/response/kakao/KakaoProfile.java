package com.onna.onnaback.global.oauth.adapter.in.web.response.kakao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoProfile {
    public String nickname;
    public String profile_image_url;
}
