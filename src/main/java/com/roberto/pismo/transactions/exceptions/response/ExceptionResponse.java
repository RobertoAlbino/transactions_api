package com.roberto.pismo.transactions.exceptions.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionResponse {

    @JsonProperty("transactions_api_error_message")
    private String errorMessage;

}
