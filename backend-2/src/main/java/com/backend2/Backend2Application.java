package com.backend2;

import com.backend2.model.Transaction;
import com.backend2.service.impl.BackendServer2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Backend2Application implements ApplicationRunner {
	@Autowired
	private BackendServer2Service backendServer2Service;
	public static void main(String[] args) {
		SpringApplication.run(Backend2Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		backendServer2Service.saveTransactions(new Transaction("failure", "143.15", "12-05-2017", "111"));
		backendServer2Service.saveTransactions(new Transaction("failure", "873.15", "11-05-2013", "112"));
		backendServer2Service.saveTransactions(new Transaction("failure", "322.15", "09-03-2016", "111"));
		backendServer2Service.saveTransactions(new Transaction("failure", "199.15", "01-08-2023", "113"));
		backendServer2Service.saveTransactions(new Transaction("failure", "89762.15", "12-11-2022", "112"));
		backendServer2Service.saveTransactions(new Transaction("failure", "545.15", "05-10-2015", "111"));
		backendServer2Service.saveTransactions(new Transaction("failure", "211.15", "27-02-2023", "113"));
	}
}
