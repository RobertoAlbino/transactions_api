package com.roberto.pismo.transactions;

import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.roberto.pismo.transactions")
@SpringBootApplication
@EnableFeignClients
@Generated
@Slf4j
public class TransactionsApplication {

	public static void main(String[] args) {
		log.info("Serviço de transações iniciado");
		SpringApplication.run(TransactionsApplication.class, args);
	}

}
