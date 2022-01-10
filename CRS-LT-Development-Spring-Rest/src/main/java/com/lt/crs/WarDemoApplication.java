package com.lt.crs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 
 * @author Siva
 * 
 *
 */

@ComponentScan({"com.lt.*"})
@EnableWebMvc
@EnableAutoConfiguration
@SpringBootApplication
@EnableJpaRepositories("com.lt.crs.repos")
@EntityScan("com.lt.crs.entity")
public class WarDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarDemoApplication.class, args);
	}

}
