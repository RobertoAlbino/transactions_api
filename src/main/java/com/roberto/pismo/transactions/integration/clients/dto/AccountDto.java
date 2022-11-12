package com.roberto.pismo.transactions.integration.clients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccountDto {

    @JsonProperty("account_id")
    private Long accountID;

    @JsonProperty("document_number")
    private Long documentNumber;

}
