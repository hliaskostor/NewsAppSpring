package com.example.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/hello")
@SpringBootApplication
public class RegisterApplication {
	public static void main(String[] args) {
		SpringApplication.run(RegisterApplication.class, args);
	}
	@GetMapping("/eden")
	public String testEndpoint() {
		return "Hello World!";
	}
}

