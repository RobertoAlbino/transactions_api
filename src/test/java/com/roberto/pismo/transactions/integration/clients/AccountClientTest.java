package com.roberto.pismo.transactions.integration.clients;

import com.roberto.pismo.transactions.exceptions.BusinessException;
import com.roberto.pismo.transactions.integration.clients.feign.AccountFeign;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountClientTest {

    @InjectMocks
    AccountClient accountClient;

    @Mock
    AccountFeign accountFeign;

    @Test
    void assertGetAccountNullIdentifierThrows() {
        Assertions.assertThrows(BusinessException.class,
                () -> accountClient.getAccount(null),
                accountClient.ACCOUNT_ID_NULL);
    }

    @Test
    void assertGetAccountThrowsAccountNotFound() {
        when(accountFeign.getAccount(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessException.class,
                () -> accountClient.getAccount(1L),
                accountClient.ACCOUNT_NOT_FOUND);
    }
}