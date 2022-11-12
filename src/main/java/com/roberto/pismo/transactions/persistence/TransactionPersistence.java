package com.roberto.pismo.transactions.persistence;

import com.roberto.pismo.transactions.persistence.mapper.TransactionPersistenceMapper;
import com.roberto.pismo.transactions.persistence.repository.TransactionRepository;
import com.roberto.pismo.transactions.business.model.TransactionModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionPersistence {

    private final TransactionRepository repository;

    public TransactionModel create(TransactionModel transaction) {
        var entity = TransactionPersistenceMapper.toEntity(transaction);
        var saved = repository.save(entity);
        return TransactionPersistenceMapper.toModel(saved);
    }
}
