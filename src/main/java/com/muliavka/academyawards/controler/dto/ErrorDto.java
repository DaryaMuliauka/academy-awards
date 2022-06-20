package com.muliavka.academyawards.controler.dto;

import lombok.Builder;
import lombok.Value;

import java.time.OffsetDateTime;

@Value
@Builder
public class ErrorDto {

    private String message;

    private OffsetDateTime timestamp;
}
