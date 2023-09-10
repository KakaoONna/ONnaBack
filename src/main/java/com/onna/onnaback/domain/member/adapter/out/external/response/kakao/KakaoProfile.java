package com.onna.onnaback.domain.member.adapter.out.external.response.kakao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoProfile {
    public String nickname;
    public String profile_image_url;
}
