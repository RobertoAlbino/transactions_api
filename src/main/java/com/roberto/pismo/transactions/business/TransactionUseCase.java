package com.roberto.pismo.transactions.business;

import com.roberto.pismo.transactions.business.validator.CreateTransactionValidator;
import com.roberto.pismo.transactions.persistence.TransactionPersistence;
import com.roberto.pismo.transactions.business.model.TransactionModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TransactionUseCase {

    private final TransactionPersistence transactionPersistence;
    private final CreateTransactionValidator createTransactionValidator;

    public TransactionModel create(TransactionModel transaction) {
        transaction.setEventDate(LocalDateTime.now());
        createTransactionValidator.validateCreate(transaction);
        return transactionPersistence.create(transaction);
    }
}
