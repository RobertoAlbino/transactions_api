package com.roberto.pismo.transactions.exceptions;

import lombok.Data;

@Data
public class IntegrationException extends RuntimeException {

    private int status;

    private String message;

    public IntegrationException(int status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

}
