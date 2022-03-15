package com.example.productlistcsv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProductListCsvApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductListCsvApplication.class, args);
	}

}
