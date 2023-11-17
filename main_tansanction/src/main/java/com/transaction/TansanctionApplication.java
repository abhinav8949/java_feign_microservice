package com.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.transaction.client")
@ComponentScan(basePackages = "com.transaction")
public class TansanctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(TansanctionApplication.class, args);
	}

}
