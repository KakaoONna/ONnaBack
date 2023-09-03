package com.onna.onnaback.domain.apply.adapter.in.web.request;

import com.onna.onnaback.domain.apply.domain.AcceptStatus;

import lombok.Getter;

@Getter
public class ApplyProcessRequest {

    Long sparkId;

    Long applicantId;

    AcceptStatus acceptStatus;
}
