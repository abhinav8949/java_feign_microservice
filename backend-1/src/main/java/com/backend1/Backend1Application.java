package com.backend1;

import com.backend1.model.Transaction;
import com.backend1.service.impl.BackendServer1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.backend1")
public class Backend1Application implements ApplicationRunner {
	@Autowired
	private BackendServer1Service backendServer1Service;

	public static void main(String[] args) {
		SpringApplication.run(Backend1Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		backendServer1Service.saveTransactions(new Transaction("success", "143.15", "12-05-2017", "111"));
		backendServer1Service.saveTransactions(new Transaction("success", "873.15", "11-05-2013", "112"));
		backendServer1Service.saveTransactions(new Transaction("success", "322.15", "09-03-2016", "111"));
		backendServer1Service.saveTransactions(new Transaction("success", "199.15", "01-08-2023", "113"));
		backendServer1Service.saveTransactions(new Transaction("success", "89762.15", "12-11-2022", "112"));
		backendServer1Service.saveTransactions(new Transaction("success", "545.15", "05-10-2015", "111"));
		backendServer1Service.saveTransactions(new Transaction("success", "211.15", "27-02-2023", "113"));
	}
}
