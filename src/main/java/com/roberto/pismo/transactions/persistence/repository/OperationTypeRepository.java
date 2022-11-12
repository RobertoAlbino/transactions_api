package com.roberto.pismo.transactions.persistence.repository;

import com.roberto.pismo.transactions.persistence.entities.OperationTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationTypeRepository extends JpaRepository<OperationTypeEntity, Integer> {}
