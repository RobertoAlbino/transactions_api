package com.roberto.pismo.transactions.business;

import com.roberto.pismo.transactions.business.validator.CreateTransactionValidator;
import com.roberto.pismo.transactions.integration.clients.AccountClient;
import com.roberto.pismo.transactions.persistence.TransactionPersistence;
import com.roberto.pismo.transactions.business.model.TransactionModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TransactionUseCase {

    private final AccountClient accountClient;
    private final TransactionPersistence transactionPersistence;
    private final CreateTransactionValidator createTransactionValidator;

    public TransactionModel create(TransactionModel transaction) {
        transaction.setEventDate(LocalDateTime.now());
        createTransactionValidator.validateCreate(transaction);
        transaction.setAmount(transaction.getAmount().setScale(2, RoundingMode.HALF_EVEN));
        accountClient.getAccount(transaction.getAccountID());
        return transactionPersistence.create(transaction);
    }

}
