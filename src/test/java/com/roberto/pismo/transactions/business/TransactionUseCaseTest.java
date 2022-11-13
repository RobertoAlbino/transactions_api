package com.roberto.pismo.transactions.business;

import com.roberto.pismo.transactions.business.model.TransactionModel;
import com.roberto.pismo.transactions.business.validator.CreateTransactionValidator;
import com.roberto.pismo.transactions.integration.clients.AccountClient;
import com.roberto.pismo.transactions.persistence.TransactionPersistence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactionUseCaseTest {

    @InjectMocks
    private TransactionUseCase transactionUseCase;

    @Mock
    private AccountClient accountClient;

    @Mock
    private TransactionPersistence transactionPersistence;

    @Mock
    private CreateTransactionValidator createTransactionValidator;

    @Test
    void testCreate() {
        transactionUseCase.create(TransactionModel.builder().build());
    }

}