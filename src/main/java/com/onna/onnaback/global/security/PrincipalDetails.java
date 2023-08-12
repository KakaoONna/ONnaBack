package com.onna.onnaback.global.security;

import com.onna.onnaback.domain.member.domain.Member;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {

    private Member member;
    private Map<String, Object> attributes;


    //일반 로그인 생성자
    public PrincipalDetails(Member member) {
        this.member=member;
    }

    //OAuth 로그인 생성자
    public PrincipalDetails(Member member, Map<String, Object> attributes ) {
        this.member=member;
        this.attributes = attributes;
    }

    /**
     * OAuth2User 인터페이스 메소드
     */
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }


    /**
     * UserDetails 인터페이스 메소드
     */
    // 해당 User의 권한을 리턴하는 곳!(role)
    // SecurityFilterChain에서 권한을 체크할 때 사용됨
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {

                return String.valueOf(member.getRole());
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return null;
    }


    @Override
    public String getUsername() {
        return member.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        //우리사이트!! 1년 동안 사용하지 않으면 휴면 계정으로 바꾼다.
        // 현재 시간 - 마지막 로그인 시간 => 1년을 초기하면 return false로 바꾼다.
        // 이러한 로직을 여기 넣는다.
        return true;
    }

    @Override
    public String getName() {
        return null;
    }
}
