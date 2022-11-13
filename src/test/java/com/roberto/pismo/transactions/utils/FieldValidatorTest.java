package com.roberto.pismo.transactions.utils;

import com.roberto.pismo.transactions.exceptions.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FieldValidatorTest {

    @Test
    void assertValidateNullThrows() {
        Assertions.assertThrows(BusinessException.class,
                                () -> FieldValidator.validateNull(null, "testField"),
                                FieldValidator.buildMessage("testField"));
    }
}