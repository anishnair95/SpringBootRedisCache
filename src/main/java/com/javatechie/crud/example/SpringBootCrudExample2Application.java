package com.javatechie.crud.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//Need this annotation to enable the caching
@EnableCaching
public class SpringBootCrudExample2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrudExample2Application.class, args);
	}

}
