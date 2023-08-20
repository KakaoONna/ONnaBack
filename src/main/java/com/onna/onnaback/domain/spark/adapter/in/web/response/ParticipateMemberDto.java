package com.onna.onnaback.domain.spark.adapter.in.web.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
// todo : Record로 바꿔보기
public class ParticipateMemberDto {

    private Long memberId;

    private String profileImg;
}
