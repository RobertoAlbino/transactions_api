package com.roberto.pismo.transactions.exceptions.handler;

import com.roberto.pismo.transactions.exceptions.BusinessException;
import com.roberto.pismo.transactions.exceptions.response.ExceptionResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class BusinessExceptionHandlerTest {

    @InjectMocks
    BusinessExceptionHandler businessExceptionHandler;

    @Test
    void handleException() {
        var response = ExceptionResponse
                .builder()
                    .errorMessage("test")
                .build();
        var handled = businessExceptionHandler.handleException(new BusinessException("test"));
        Assertions.assertEquals(response, handled.getBody());
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.ordinal(), handled.getStatusCode().ordinal());
    }
}