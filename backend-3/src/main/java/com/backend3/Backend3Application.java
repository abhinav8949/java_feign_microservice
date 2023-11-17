package com.backend3;

import com.backend3.model.Transaction;
import com.backend3.service.impl.BackendServer3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Backend3Application implements ApplicationRunner {
	@Autowired
	private BackendServer3Service backendServer3Service;
	public static void main(String[] args) {
		SpringApplication.run(Backend3Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		backendServer3Service.saveTransactions(new Transaction("pending", "143.15", "12-05-2017", "111"));
		backendServer3Service.saveTransactions(new Transaction("pending", "873.15", "11-05-2013", "112"));
		backendServer3Service.saveTransactions(new Transaction("pending", "322.15", "09-03-2016", "111"));
		backendServer3Service.saveTransactions(new Transaction("pending", "199.15", "01-08-2023", "113"));
		backendServer3Service.saveTransactions(new Transaction("pending", "89762.15", "12-11-2022", "112"));
		backendServer3Service.saveTransactions(new Transaction("pending", "545.15", "05-10-2015", "111"));
		backendServer3Service.saveTransactions(new Transaction("pending", "211.15", "27-02-2023", "113"));

	}
}
