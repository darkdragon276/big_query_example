package com.backend.bigquery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EntityScan(basePackages = {"com.backend.bigquery.entity"})
//@EnableJpaRepositories(basePackages = {"com.backend.bigquery.entity"})
public class BigQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BigQueryApplication.class, args);
	}
}
