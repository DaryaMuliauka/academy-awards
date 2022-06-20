package com.muliavka.academyawards.controler;

import com.muliavka.academyawards.controler.dto.ErrorDto;
import com.muliavka.academyawards.exception.IdentifiersNotEqualException;
import com.muliavka.academyawards.exception.MovieNotFoundException;
import com.muliavka.academyawards.exception.RatingAlreadyExistsException;
import com.muliavka.academyawards.exception.RatingNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.OffsetDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ErrorDto> handleMovieNotFoundException(MovieNotFoundException ex) {
        return new ResponseEntity<>(buildErrorDto(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RatingNotFoundException.class)
    public ResponseEntity<ErrorDto> handleRatingNotFoundException(RatingNotFoundException ex) {
        return new ResponseEntity<>(buildErrorDto(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RatingAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> handleRatingAlreadyExistsException(RatingAlreadyExistsException ex) {
        return new ResponseEntity<>(buildErrorDto(ex.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IdentifiersNotEqualException.class)
    public ResponseEntity<ErrorDto> handleIdentifiersNotEqualException(IdentifiersNotEqualException ex) {
        return new ResponseEntity<>(buildErrorDto(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleConstraintViolationExceptionException(ConstraintViolationException ex) {
        return new ResponseEntity<>(buildErrorDto(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    private ErrorDto buildErrorDto(String message) {
        return ErrorDto.builder()
                .message(message)
                .timestamp(OffsetDateTime.now())
                .build();
    }
}
