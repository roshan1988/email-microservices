package com.owler.email.lifecycle.manager.decisionengine;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.owler.email.lifecycle.manager.deploymentengine.DeploymentEngine;
import com.owler.email.lifecycle.manager.deploymentrules.DeploymentRules;
import com.owler.email.lifecycle.manager.scalingpolicy.ScalingPolicies;

@Component
public class DecisionEngine{
	
	@Autowired
	ScalingPolicies scalingPolicies;
	
	@Autowired
	DeploymentEngine deploymentEngine;
	
	@Autowired
	DeploymentRules deploymentRules;
	
	public boolean execute(String serviceId, Map<?, ?> metrics){
		if(scalingPolicies.getScaleUpPolicy(serviceId).execute(serviceId, metrics)){		
			return deploymentEngine.scaleUp(deploymentRules.getScaleUpDeploymentRules(serviceId), serviceId);	
		}
		else if(scalingPolicies.getScaleDownPolicy(serviceId).execute(serviceId, metrics)){		
			return deploymentEngine.scaleDown(deploymentRules.getScaleDownDeploymentRules(serviceId), serviceId);	
		}
		return false;
	}
}