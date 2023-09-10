package com.onna.onnaback.domain.member.adapter.out.external.response.kakao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoAccount {
    @JsonProperty("profile")
    public KakaoProfile profile;

    @JsonProperty("email")
    public String email;

    @JsonProperty("name")
    public String name;

    @JsonProperty("age_range")
    public String age_range;

    @JsonProperty("birthday")
    public String birthDay;

    @JsonProperty("gender")
    public String gender;


}
