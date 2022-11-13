package com.roberto.pismo.transactions.integration.clients;

import com.roberto.pismo.transactions.exceptions.BusinessException;
import com.roberto.pismo.transactions.integration.clients.dto.AccountDto;
import com.roberto.pismo.transactions.integration.clients.feign.AccountFeign;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AccountClient {

    public final String ACCOUNT_ID_NULL = "O identificador da conta não pode ser nulo";
    public final String ACCOUNT_NOT_FOUND = "A conta não foi encontrada";

    private final AccountFeign accountFeign;

    public AccountDto getAccount(Long accountId) {
        if (Objects.isNull(accountId)) {
            throw new BusinessException(ACCOUNT_ID_NULL);
        }
        return accountFeign.getAccount(accountId)
                .orElseThrow(() -> new BusinessException(ACCOUNT_NOT_FOUND));
    }
}
