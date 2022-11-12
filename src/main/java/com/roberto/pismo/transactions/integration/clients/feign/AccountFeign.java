package com.roberto.pismo.transactions.integration.clients.feign;

import com.roberto.pismo.transactions.integration.clients.dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@FeignClient(name = "AccountFeign", url = "${integration.clients.url}")
public interface AccountFeign {

    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{accountId}")
    Optional<AccountDto> getAccount(@PathVariable Long accountId);

}
