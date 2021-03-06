package com.owler.email.lifecycle.manager.metricscollector;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.owler.email.lifecycle.manager.decisionengine.DecisionEngine;

@Component
public class MetricsCollector{
	
	@Autowired
	DiscoveryClient eurekaClient;
	
	@Autowired
	DecisionEngine decisionEngine;
	
	@Autowired
 	private RestTemplate restTemplate;
	
	@SuppressWarnings("rawtypes")
	public void start(){
		while(true){ 
			eurekaClient.getServices().forEach(service -> {	
				Map metrics = restTemplate.getForObject("http://"+service+"/metrics",Map.class);
				decisionEngine.execute(service, metrics);
			});	
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
}

@Configuration
class AppConfiguration {

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}