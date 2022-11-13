package com.roberto.pismo.transactions.persistence;

import com.roberto.pismo.transactions.exceptions.BusinessException;
import com.roberto.pismo.transactions.persistence.repository.OperationTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OperationTypePersistence {

    public final static String OPERATION_NOT_FOUND = "Tipo de operação não encontrada";

    private final OperationTypeRepository repository;

    public void exists(Integer operationTypeID) {
        repository.findById(operationTypeID).orElseThrow(() ->
                    new BusinessException(OPERATION_NOT_FOUND));
    }

}
