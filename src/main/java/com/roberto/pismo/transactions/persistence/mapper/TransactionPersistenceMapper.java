package com.roberto.pismo.transactions.persistence.mapper;

import com.roberto.pismo.transactions.persistence.entities.TransactionEntity;
import com.roberto.pismo.transactions.business.model.TransactionModel;

public class TransactionPersistenceMapper {

    public static TransactionEntity toEntity(TransactionModel model) {
        return TransactionEntity
                .builder()
                    .accountID(model.getAccountID())
                    .operationTypeID(model.getOperationTypeID())
                    .amount(model.getAmount())
                    .eventDate(model.getEventDate())
                .build();
    }

    public static TransactionModel toModel(TransactionEntity entity) {
        return TransactionModel
                .builder()
                    .id(entity.getId())
                    .accountID(entity.getAccountID())
                    .operationTypeID(entity.getOperationTypeID())
                    .amount(entity.getAmount())
                    .eventDate(entity.getEventDate())
                .build();
    }
}
