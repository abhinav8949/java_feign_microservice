package com.mini_lib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.mini_lib")
public class MiniLibApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniLibApplication.class, args);
	}

}
