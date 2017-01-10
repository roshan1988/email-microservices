package com.owler.email.lifecycle.manager.deploymentengine;

import org.springframework.stereotype.Component;

import com.owler.email.lifecycle.manager.deploymentrules.DeploymentRule;

@Component
public class DeploymentEngine{
		
	public boolean scaleUp(DeploymentRule rule, String serviceId){
		if(! rule.execute()) {
			return false;
		}
		Runnable runnable = () -> {
			System.out.println("Kicking off a new instance "+ serviceId);	
		};

		Thread thread = new Thread(runnable);
		thread.start();
		
		return true;
	}
	
	public boolean scaleDown(DeploymentRule rule, String serviceId){
		if(! rule.execute()) {
			return false;
		}
		System.out.println("Scaling down instance : " + serviceId);
		return true;
	}

}