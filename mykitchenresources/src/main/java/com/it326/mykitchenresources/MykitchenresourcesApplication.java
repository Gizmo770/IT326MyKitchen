package com.it326.mykitchenresources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.it326.mykitchenresources.*")
@ComponentScan(basePackages = "com.it326.mykitchenresources.*")
@EntityScan("com.it326.mykitchenresources.*")  
public class MykitchenresourcesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MykitchenresourcesApplication.class, args);
	}

}
