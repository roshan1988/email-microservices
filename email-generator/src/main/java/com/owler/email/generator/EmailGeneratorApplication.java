package com.owler.email.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * @author Roshan Alexander
 *
 */
@EnableDiscoveryClient 
@SpringBootApplication
@EnableCircuitBreaker
@EnableSwagger2
public class EmailGeneratorApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EmailGeneratorApplication.class, args);
	}
	
}
