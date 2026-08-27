package com.davisanttana.vendas_api.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.davisanttana.vendas_api.model")
public class VendasApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(VendasApiApplication.class, args);
	}

}
