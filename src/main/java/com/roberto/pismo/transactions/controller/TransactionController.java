package com.roberto.pismo.transactions.controller;

import com.roberto.pismo.transactions.controller.mapper.TransactionRequestMapper;
import com.roberto.pismo.transactions.controller.request.TransactionRequest;
import com.roberto.pismo.transactions.business.TransactionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("transactions")
public class TransactionController {

    private final TransactionUseCase transactionUseCase;

    @PostMapping
    public ResponseEntity create(@RequestBody TransactionRequest request) {
        var model = TransactionRequestMapper.toModel(request);
        var entity = transactionUseCase.create(model);
        return ResponseEntity.ok(entity);
    }

}
