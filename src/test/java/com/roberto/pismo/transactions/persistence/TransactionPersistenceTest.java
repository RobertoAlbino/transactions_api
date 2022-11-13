package com.roberto.pismo.transactions.persistence;

import com.roberto.pismo.transactions.business.enums.OperationTypeEnum;
import com.roberto.pismo.transactions.business.model.TransactionModel;
import com.roberto.pismo.transactions.persistence.entities.TransactionEntity;
import com.roberto.pismo.transactions.persistence.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionPersistenceTest {

    @InjectMocks
    TransactionPersistence transactionPersistence;

    @Mock
    TransactionRepository transactionRepository;

    @Test
    void testCreateNotThrows() {
        var model = TransactionModel
                .builder()
                    .id(1L)
                    .accountID(1L)
                    .operationTypeID(OperationTypeEnum.COMPRA_A_VISTA.operationTypeID)
                    .amount(BigDecimal.ONE)
                    .eventDate(LocalDateTime.now())
                .build();
        when(transactionRepository.save(any())).thenReturn(TransactionEntity.builder().build());
        Assertions.assertDoesNotThrow(() -> transactionPersistence.create(model));
    }
}