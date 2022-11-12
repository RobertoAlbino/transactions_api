package com.roberto.pismo.transactions.controller.mapper;

import com.roberto.pismo.transactions.controller.request.TransactionRequest;
import com.roberto.pismo.transactions.business.model.TransactionModel;

public class TransactionRequestMapper {

    public static TransactionModel toModel(TransactionRequest request) {
        return TransactionModel
                .builder()
                .accountID(request.getAccountID())
                .operationTypeID(request.getOperationTypeID())
                .amount(request.getAmount())
                .build();
    }
}
