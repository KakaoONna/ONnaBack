package com.onna.onnaback.global.security;

import com.onna.onnaback.domain.member.adapter.out.persistence.MemberRepository;
import com.onna.onnaback.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalService implements UserDetailsService {

    private final MemberRepository memberRepository;

    //시큐리티 session => Authentication => UserDetails
    // 여기서 리턴 된 값이 Authentication 안에 들어간다.(리턴될때 들어간다.)
    // 그리고 시큐리티 session 안에 Authentication 이 들어간다.
    //함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member findUser = memberRepository.findByName(username).orElseThrow();
        if(findUser!=null) {
            return new PrincipalDetails(findUser);
        }

        return null;
    }
}