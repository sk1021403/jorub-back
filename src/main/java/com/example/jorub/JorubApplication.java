package com.example.jorub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JorubApplication {

	public static void main(String[] args) {
		SpringApplication.run(JorubApplication.class, args);
	}

}
