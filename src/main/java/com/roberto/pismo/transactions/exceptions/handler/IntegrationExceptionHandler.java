package com.roberto.pismo.transactions.exceptions.handler;

import com.roberto.pismo.transactions.exceptions.IntegrationException;
import com.roberto.pismo.transactions.exceptions.response.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IntegrationExceptionHandler {

    @ExceptionHandler(IntegrationException.class)
    public ResponseEntity handleException(IntegrationException integrationException) {
        var response = ExceptionResponse
                .builder()
                    .errorMessage(integrationException.getMessage())
                .build();
        return ResponseEntity
                .status(integrationException.getStatus())
                .body(response);
    }

}