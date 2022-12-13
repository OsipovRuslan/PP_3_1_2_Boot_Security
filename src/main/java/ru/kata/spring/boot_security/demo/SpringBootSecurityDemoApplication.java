package ru.kata.spring.boot_security.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


@SpringBootApplication
public class SpringBootSecurityDemoApplication {

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);

	}
}
