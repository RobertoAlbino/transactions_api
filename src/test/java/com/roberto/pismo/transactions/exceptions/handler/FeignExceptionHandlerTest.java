package com.roberto.pismo.transactions.exceptions.handler;

import com.roberto.pismo.transactions.exceptions.response.ExceptionResponse;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class FeignExceptionHandlerTest {

    @InjectMocks
    FeignExceptionHandler feignExceptionHandler;

    @Test
    void handleException() {
        var response = ExceptionResponse
                .builder()
                    .errorMessage("FORBIDDEN")
                .build();
        var feignException = new FeignExceptionTest(403, "FORBIDDEN");
        var handled = feignExceptionHandler.handleException(feignException);
        Assertions.assertEquals(response, handled.getBody());
        Assertions.assertEquals(HttpStatus.FORBIDDEN.ordinal(), handled.getStatusCode().ordinal());
    }
}