package com.roberto.pismo.transactions.persistence.mapper;

import com.roberto.pismo.transactions.business.enums.OperationTypeEnum;
import com.roberto.pismo.transactions.business.model.TransactionModel;
import com.roberto.pismo.transactions.persistence.entities.TransactionEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class TransactionPersistenceMapperTest {

    @Test
    void toEntity() {
        var eventDate = LocalDateTime.now();
        var model = TransactionModel
                .builder()
                    .accountID(1L)
                    .operationTypeID(OperationTypeEnum.COMPRA_A_VISTA.operationTypeID)
                    .amount(BigDecimal.ONE)
                    .eventDate(eventDate)
                .build();
        var entity = TransactionPersistenceMapper.toEntity(model);
        Assertions.assertEquals(1L, entity.getAccountID());
        Assertions.assertEquals(OperationTypeEnum.COMPRA_A_VISTA.operationTypeID, entity.getOperationTypeID());
        Assertions.assertEquals(BigDecimal.ONE, entity.getAmount());
        Assertions.assertEquals(eventDate, entity.getEventDate());
    }

    @Test
    void toModel() {
        var eventDate = LocalDateTime.now();
        var entity = TransactionEntity
                .builder()
                    .id(1L)
                    .accountID(1L)
                    .operationTypeID(OperationTypeEnum.COMPRA_A_VISTA.operationTypeID)
                    .amount(BigDecimal.ONE)
                    .eventDate(eventDate)
                .build();
        var model = TransactionPersistenceMapper.toModel(entity);
        Assertions.assertEquals(1L, model.getId());
        Assertions.assertEquals(1L, model.getAccountID());
        Assertions.assertEquals(OperationTypeEnum.COMPRA_A_VISTA.operationTypeID, model.getOperationTypeID());
        Assertions.assertEquals(BigDecimal.ONE, model.getAmount());
        Assertions.assertEquals(eventDate, model.getEventDate());
    }
}