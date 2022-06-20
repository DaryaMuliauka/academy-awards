package com.muliavka.academyawards.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class IdentifiersNotEqualException extends RuntimeException {

    public IdentifiersNotEqualException(String message) {
        super(message);
    }
}
