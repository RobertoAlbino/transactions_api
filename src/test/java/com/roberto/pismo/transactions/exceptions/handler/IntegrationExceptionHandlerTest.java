package com.roberto.pismo.transactions.exceptions.handler;

import com.roberto.pismo.transactions.exceptions.IntegrationException;
import com.roberto.pismo.transactions.exceptions.response.ExceptionResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class IntegrationExceptionHandlerTest {

    @InjectMocks
    IntegrationExceptionHandler integrationExceptionHandler;

    @Test
    void handleException() {
        var response = ExceptionResponse
                .builder()
                    .errorMessage("test")
                .build();
        var handled = integrationExceptionHandler.handleException(new IntegrationException(403, "test"));
        Assertions.assertEquals(response, handled.getBody());
        Assertions.assertEquals(HttpStatus.FORBIDDEN.ordinal(), handled.getStatusCode().ordinal());
    }
}