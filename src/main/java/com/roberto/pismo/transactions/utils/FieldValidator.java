package com.roberto.pismo.transactions.utils;

import com.roberto.pismo.transactions.exceptions.BusinessException;

import java.util.Objects;

public class FieldValidator {

    public static String buildMessage(String fieldName) {
        return String.format("O campo %s não pode ser nulo", fieldName);
    }

    public static void validateNull(Object field, String fieldName) {
        if (Objects.isNull(field)) {
            throw new BusinessException(buildMessage(fieldName));
        }
    }

}
