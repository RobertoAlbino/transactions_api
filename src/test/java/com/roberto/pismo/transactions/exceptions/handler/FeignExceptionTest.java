package com.roberto.pismo.transactions.exceptions.handler;

import feign.FeignException;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class FeignExceptionTest extends FeignException {

    public FeignExceptionTest(int status, String message) {
        super(status, message, message.getBytes(StandardCharsets.UTF_8), new HashMap<>());
    }

}
