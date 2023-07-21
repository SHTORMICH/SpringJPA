package com.epam.kabaldin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@SpringBootApplication
@ComponentScan("com.epam.kabaldin")
@EnableCouchbaseRepositories("com.epam.kabaldin.repository")
public class Module10Application {

	public static void main(String[] args) {
		SpringApplication.run(Module10Application.class, args);
	}
}
