package com.roberto.pismo.transactions.exceptions.handler;

import com.roberto.pismo.transactions.exceptions.BusinessException;
import com.roberto.pismo.transactions.exceptions.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity handleException(BusinessException businessException) {
        var response = ExceptionResponse
                .builder()
                    .errorMessage(businessException.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

}