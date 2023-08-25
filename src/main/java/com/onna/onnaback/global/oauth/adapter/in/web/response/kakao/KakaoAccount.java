package com.onna.onnaback.global.oauth.adapter.in.web.response.kakao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.onna.onnaback.domain.member.domain.Age;
import com.onna.onnaback.domain.member.domain.Gender;
import lombok.Getter;
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoAccount {
    @JsonProperty("profile")
    public KakaoProfile profile;
    public String email;
    public String name;
    public String age_range;
    public String birthDay;
    public String gender;


}
