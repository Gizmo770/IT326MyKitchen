package com.it326.mykitchenresources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.it326.mykitchenresources.entities")
public class MykitchenresourcesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MykitchenresourcesApplication.class, args);
	}

}
