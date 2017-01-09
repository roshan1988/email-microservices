package com.owler.email.eventcheckin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.owler.email.eventcheckin.repository.CheckinRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient 
@EnableSwagger2 
public class EmailEventCheckin {

	@Autowired
	CheckinRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(EmailEventCheckin.class, args);
	}
	
}
