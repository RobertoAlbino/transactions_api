package com.roberto.pismo.transactions.persistence;

import com.roberto.pismo.transactions.exceptions.BusinessException;
import com.roberto.pismo.transactions.persistence.entities.OperationTypeEntity;
import com.roberto.pismo.transactions.persistence.repository.OperationTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperationTypePersistenceTest {

    @InjectMocks
    OperationTypePersistence operationTypePersistence;

    @Mock
    OperationTypeRepository operationTypeRepository;

    @Test
    void exists() {
        when(operationTypeRepository.findById(any())).thenReturn(Optional.of(new OperationTypeEntity()));
        operationTypePersistence.exists(1);
    }

    @Test
    void whenNotExistsThrows() {
        when(operationTypeRepository.findById(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessException.class,
                () -> operationTypePersistence.exists(1),
                operationTypePersistence.OPERATION_NOT_FOUND);
    }
}