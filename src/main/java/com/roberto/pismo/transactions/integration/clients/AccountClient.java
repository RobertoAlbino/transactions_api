package com.roberto.pismo.transactions.integration.clients;

import com.roberto.pismo.transactions.exceptions.BusinessException;
import com.roberto.pismo.transactions.exceptions.IntegrationException;
import com.roberto.pismo.transactions.integration.clients.dto.AccountDto;
import com.roberto.pismo.transactions.integration.clients.feign.AccountFeign;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AccountClient {

    private final AccountFeign accountFeign;

    public AccountDto getAccount(Long accountId) {
        if (Objects.isNull(accountId)) {
            throw new BusinessException("O identificador da conta não pode ser nulo");
        }
        try {
            return accountFeign.getAccount(accountId)
                    .orElseThrow(() -> new BusinessException("A conta não foi encontrada"));
        } catch (FeignException feignException) {
            throw new IntegrationException(feignException.status(), feignException.getMessage());
        }
    }
}
