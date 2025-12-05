package com.shahin.TodoAPISpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApiSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApiSpringApplication.class, args); // this line starts the Spring Boot application by invoking
		// the run method of the SpringApplication class, passing in the current class (TodoApiSpringApplication) and any command-line arguments.
	}

}

// in url bar you can only send get request but to use post request you have to use postman or any other api testing tool or javascript fetch api
// but why? because in url bar you can only send get request
// but to send post request you have to use postman or any other api testing tool or javascript fetch api

