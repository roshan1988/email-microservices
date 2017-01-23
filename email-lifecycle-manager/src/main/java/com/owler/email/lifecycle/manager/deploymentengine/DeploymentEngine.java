package com.owler.email.lifecycle.manager.deploymentengine;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.owler.email.lifecycle.manager.deploymentrules.DeploymentRule;


@Component
public class DeploymentEngine {

	@Value("${com.owler.email.lifecycle.manager.marathon.endpoint}")
	private String marathonEndpoint;

	@Autowired
	DiscoveryClient eurekaClient;

	private static final Logger logger = LoggerFactory.getLogger(DeploymentEngine.class);

	public boolean scaleUp(DeploymentRule rule, String serviceId) {
		if (!rule.execute()) {
			return false;
		}
		List<ServiceInstance> currentInstances = eurekaClient.getInstances(serviceId);
		int instanceCount = currentInstances.size();
		instanceCount++;
		logger.info("Kicking off a new instance " + serviceId);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<String>("{ \"instances\" : \""+ instanceCount +"\" }", headers);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> postResponse = restTemplate.exchange(marathonEndpoint + "v2/apps/" + serviceId, HttpMethod.PUT,
					entity, String.class);
			if (postResponse.getStatusCode() != HttpStatus.OK) {
				logger.error(
						"Error upscaling the instance : " + serviceId + "Error Code : " + postResponse.getStatusCode());
			}
		} catch (Exception e) {
			logger.error("Error adding new instance for service : " + serviceId, e);
		}
		return true;
	}

	public boolean scaleDown(DeploymentRule rule, String serviceId) {
		if (!rule.execute()) {
			return false;
		}
		List<ServiceInstance> currentInstances = eurekaClient.getInstances(serviceId);
		int instanceCount = currentInstances.size();
		instanceCount--;
		logger.info("Scaling down instance : " + serviceId);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<String>("{ instances : "+ instanceCount +" }", headers);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> postResponse = restTemplate.exchange(marathonEndpoint + "v2/apps/" + serviceId,
					HttpMethod.PUT, entity, String.class);
			if (postResponse.getStatusCode() != HttpStatus.OK) {
				logger.error(
						"Error upscaling the instance : " + serviceId + "Error Code : " + postResponse.getStatusCode());
			}
		} catch (Exception e) {
			logger.error("Error adding new instance for service : " + serviceId, e);
		}
		return true;
	}

}