package org.springframework.samples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication()
public class EndOfLineApplication {

	public static void main(String[] args) {
		SpringApplication.run(EndOfLineApplication.class, args);
	}

} 

