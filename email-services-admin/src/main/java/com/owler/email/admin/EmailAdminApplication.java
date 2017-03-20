package com.owler.email.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import de.codecentric.boot.admin.config.EnableAdminServer;

/**
 * @author Roshan Alexander
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class EmailAdminApplication {
	
  public static void main(String[] args) {
    SpringApplication.run(EmailAdminApplication.class, args);
  }
  
}