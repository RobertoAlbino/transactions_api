package com.roberto.pismo.transactions.business.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionModel {

    private Long id;

    private Long accountID;

    private Integer operationTypeID;

    private BigDecimal amount;

    private LocalDateTime eventDate;

}
