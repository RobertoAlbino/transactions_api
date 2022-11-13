package com.roberto.pismo.transactions.controller.mapper;

import com.roberto.pismo.transactions.business.enums.OperationTypeEnum;
import com.roberto.pismo.transactions.controller.request.TransactionRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class TransactionRequestMapperTest {

    @Test
    void testMapper() {
        var request = TransactionRequest
                .builder()
                    .operationTypeID(OperationTypeEnum.COMPRA_A_VISTA.operationTypeID)
                    .accountID(1L)
                    .amount(BigDecimal.ONE)
                .build();
        var model = TransactionRequestMapper.toModel(request);
        Assertions.assertEquals(OperationTypeEnum.COMPRA_A_VISTA.operationTypeID, model.getOperationTypeID());
        Assertions.assertEquals(1L, model.getAccountID());
        Assertions.assertEquals(BigDecimal.ONE, model.getAmount());
    }

}