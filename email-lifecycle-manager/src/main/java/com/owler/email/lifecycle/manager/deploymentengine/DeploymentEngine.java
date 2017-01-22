package com.owler.email.lifecycle.manager.deploymentengine;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import com.owler.email.lifecycle.manager.deploymentrules.DeploymentRule;

import mesosphere.marathon.client.Marathon;
import mesosphere.marathon.client.MarathonClient;
import mesosphere.marathon.client.model.v2.App;

@Component
public class DeploymentEngine {

	@Value("${com.owler.email.lifecycle.manager.marathon.endpoint}")
	private String marathonEndpoint;

	@Autowired
	DiscoveryClient eurekaClient;

	Marathon marathon;
	
	private static final Logger logger = LoggerFactory.getLogger(DeploymentEngine.class);


	@PostConstruct
	public void init() {
		marathon = MarathonClient.getInstance(marathonEndpoint);
	}

	public boolean scaleUp(DeploymentRule rule, String serviceId) {
		if (!rule.execute()) {
			return false;
		}
		logger.info("Kicking off a new instance " + serviceId);
		App app = getAppForServiceId(serviceId);
		int instanceCount = app.getInstances();
		app.setInstances(++instanceCount);
		marathon.updateApp(serviceId, app);
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