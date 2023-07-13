package com.financy.financy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FinancyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancyApplication.class, args);
	}

}
