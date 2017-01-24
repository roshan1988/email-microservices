package com.owler.email.lifecycle.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.owler.email.lifecycle.manager.deploymentengine.DeploymentEngine;
import com.owler.email.lifecycle.manager.metricscollector.MetricsCollector;
@EnableDiscoveryClient
@EnableAutoConfiguration
public class EmailLifecycleManager {
	
	public static void main(String[] args) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<String>("{ \"instances\" : \""+ 2 +"\" }", headers);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> postResponse = restTemplate.exchange("http://192.168.99.100:8080/v2/apps//email-event-checkin-apigateway", HttpMethod.PUT,
					entity, String.class);
			if (postResponse.getStatusCode() != HttpStatus.OK) {
				System.out.println(
						"Error upscaling the instance :  Error Code : " + postResponse.getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}