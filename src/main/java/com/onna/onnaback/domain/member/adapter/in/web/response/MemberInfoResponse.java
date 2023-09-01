package com.onna.onnaback.domain.member.adapter.in.web.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberInfoResponse {

    private Long memberId;

    private String name;

    private String profileImg;

    private String email;
}
