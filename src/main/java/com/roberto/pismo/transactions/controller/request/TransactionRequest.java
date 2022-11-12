package com.roberto.pismo.transactions.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequest {

    @JsonProperty("account_id")
    private Long accountID;

    @JsonProperty("operation_type_id")
    private Integer operationTypeID;

    @JsonProperty("amount")
    private BigDecimal amount;

}
