package com.roberto.pismo.transactions.exceptions.handler;

import com.roberto.pismo.transactions.exceptions.response.ExceptionResponse;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FeignExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity handleException(FeignException feignException) {
        var response = ExceptionResponse
                .builder()
                    .errorMessage(feignException.contentUTF8())
                .build();
        return ResponseEntity
                .status(feignException.status())
                .body(response);
    }

}