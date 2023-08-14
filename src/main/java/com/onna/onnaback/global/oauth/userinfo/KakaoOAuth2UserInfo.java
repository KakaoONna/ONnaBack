package com.onna.onnaback.global.oauth.userinfo;

import com.onna.onnaback.domain.member.domain.Age;
import com.onna.onnaback.domain.member.domain.Gender;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getNickname() {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");

        if (account == null || profile == null) {
            return null;
        }

        return (String) profile.get("nickname");
    }

    @Override
    public String getImageUrl() {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");

        if (account == null || profile == null) {
            return null;
        }

        return (String) profile.get("thumbnail_image_url");
    }

    @Override
    public String getBirthDate() {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        String birthDay = (String) account.get("birthday");

        return birthDay;
    }

    @Override
    public Age getAgeRange() {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        String ageRange = (String) account.get("age_range");
        switch (ageRange) {
            case "15~19":
                return Age.TEENS;
            case "20~29":
                return Age.TWENTIES;
            case "30~39":
                return Age.THIRTIES;
            case "40~49":
                return Age.FORTIES;
            case "50~59":
                return Age.FIFTIES;
            case "60~69":
                return Age.SIXTIES;
        }
        return Age.SEVENTIES;

    }

    @Override
    public Gender getGender() {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        String gender = (String) account.get("gender");
        if(gender.equals("female")){
            return Gender.WOMAN;
        }
        return Gender.MAN;

    }

    @Override
    public String getEmail(){

        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        System.err.println(account);
        String email = (String) account.get("email");
        return  email;
    }

    @Override
    public String getPhoneNum() {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        String phoneNumber = (String) account.get("phone_number");
        return  phoneNumber;
    }
}
