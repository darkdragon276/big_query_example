package com.backend.bigquery;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})

@SpringBootApplication
//@EntityScan(basePackages = {"com.backend.bigquery.entity"})
//@EnableJpaRepositories(basePackages = {"com.backend.bigquery.entity"})
public class BigQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BigQueryApplication.class, args);
	}
}
