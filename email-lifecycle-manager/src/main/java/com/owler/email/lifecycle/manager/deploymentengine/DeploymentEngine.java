package com.owler.email.lifecycle.manager.deploymentengine;

import java.util.List;

import javax.annotation.PostConstruct;

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

import com.amazonaws.util.json.JSONObject;
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
		JSONObject request = new JSONObject();
		request.put("instances", instanceCount);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> postResponse = restTemplate.exchange(urlString, HttpMethod.POST, entity, String.class);
		if (postResponse.getStatusCode() == HttpStatus.OK) {
			JSONObject userJson = new JSONObject(postResponse.getBody());
		} else {
			logger.error("Error upscaling the instance : ");
		}
		return true;
	}

	private App getAppForServiceId(String serviceId) {
		List<ServiceInstance> currentInstances = eurekaClient.getInstances(serviceId);
		App app = new App();
		app.setId(serviceId);
		app.setInstances(currentInstances.size());
		return app;
	}

	public boolean scaleDown(DeploymentRule rule, String serviceId) {
		if (!rule.execute()) {
			return false;
		}
		logger.info("Scaling down instance : " + serviceId);
		App app = getAppForServiceId(serviceId);
		int instanceCount = app.getInstances();
		app.setInstances(--instanceCount);
		marathon.updateApp(serviceId, app);
		return true;
	}

}