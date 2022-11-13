package com.roberto.pismo.transactions.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roberto.pismo.transactions.business.TransactionUseCase;
import com.roberto.pismo.transactions.business.enums.OperationTypeEnum;
import com.roberto.pismo.transactions.controller.request.TransactionRequest;
import com.roberto.pismo.transactions.persistence.repository.OperationTypeRepository;
import com.roberto.pismo.transactions.persistence.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = TransactionController.class)
@ImportAutoConfiguration({FeignAutoConfiguration.class})
class TransactionControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    OperationTypeRepository operationTypeRepository;

    @MockBean
    TransactionRepository transactionRepository;

    @MockBean
    TransactionUseCase transactionUseCase;

    @Test
    void testCreateEndpoint() throws Exception {

        var transaction = TransactionRequest
                .builder()
                    .accountID(1L)
                    .operationTypeID(OperationTypeEnum.COMPRA_A_VISTA.operationTypeID)
                    .amount(new BigDecimal(-1))
                .build();

        MockHttpServletResponse response = mvc.perform(post("/transactions")
                                              .content(objectMapper.writeValueAsString(transaction))
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .accept(MediaType.APPLICATION_JSON))
                                              .andReturn()
                                              .getResponse();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}