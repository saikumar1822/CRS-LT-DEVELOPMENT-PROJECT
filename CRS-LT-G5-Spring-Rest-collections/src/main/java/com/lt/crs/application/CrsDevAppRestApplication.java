package com.lt.crs.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@ComponentScan({"com.lt.*"})
@EnableWebMvc
@EnableAutoConfiguration
@SpringBootApplication
public class CrsDevAppRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrsDevAppRestApplication.class, args);
	}

}
