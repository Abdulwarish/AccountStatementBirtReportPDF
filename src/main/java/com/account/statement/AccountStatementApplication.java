package com.account.statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountStatementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountStatementApplication.class, args);
	}
	
}
