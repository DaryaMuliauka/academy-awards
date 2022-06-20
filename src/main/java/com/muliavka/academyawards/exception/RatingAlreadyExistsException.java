package com.muliavka.academyawards.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RatingAlreadyExistsException extends RuntimeException {

    public RatingAlreadyExistsException(String message) {
        super(message);
    }
}
