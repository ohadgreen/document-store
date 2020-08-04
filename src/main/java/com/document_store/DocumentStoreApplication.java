package com.document_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class DocumentStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentStoreApplication.class, args);
	}

}
