package com.owler.email.lifecycle.manager.deploymentrules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class DeploymentRules {

	@Value("${com.owler.email.lifecycle.manager.deploymentrules.maxinstance}")
	private String maxInstances;
	
	@Value("${com.owler.email.lifecycle.manager.deploymentrules.mininstance}")
	private String minInstances;

	@Autowired
	DiscoveryClient eurekaClient;

	public DeploymentRule getScaleUpDeploymentRules(String serviceId) {
		List<ServiceInstance> currentInstances = eurekaClient.getInstances(serviceId);
		return new MaxInstanceDeploymentRule(Integer.parseInt(maxInstances), currentInstances.size());
	}
	
	public DeploymentRule getScaleDownDeploymentRules(String serviceId) {
		List<ServiceInstance> currentInstances = eurekaClient.getInstances(serviceId);
		return new MinInstanceDeploymentRule(Integer.parseInt(minInstances), currentInstances.size());
	}

}