package com.davisanttana.vendas_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
public class VendasApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(VendasApiApplication.class, args);
	}

}
