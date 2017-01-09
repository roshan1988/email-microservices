package com.owler.email.lifecycle.manager.deploymentrules;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class DeploymentRules{
	
	@Value("${com.owler.email.lifecycle.manager.deploymentrules.maxinstance}")
	private String maxInstances;
	
	public DeploymentRule getDeploymentRules(String serviceId){
		return new MaxInstanceDeploymentRule(Integer.parseInt(maxInstances));
	}
	
}