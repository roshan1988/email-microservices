package com.owler.email.lifecycle.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.owler.email.lifecycle.manager.metricscollector.MetricsCollector;
@EnableDiscoveryClient
@SpringBootApplication
@EnableAutoConfiguration
public class EmailLifecycleManager implements CommandLineRunner {
	
	@Autowired
	MetricsCollector metricsCollector;
	
	public static void main(String[] args) {
		SpringApplication.run(EmailLifecycleManager.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		metricsCollector.start();
	}
}