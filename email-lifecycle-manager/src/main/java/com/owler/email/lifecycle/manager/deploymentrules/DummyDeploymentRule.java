package com.owler.email.lifecycle.manager.deploymentrules;

import com.owler.email.lifecycle.manager.deploymentengine.DeploymentEngine;

public class DummyDeploymentRule implements DeploymentRule {
	private static final int max_instance=2;
	
	public boolean execute(){
		if(DeploymentEngine.num_instances >= max_instance) return false;
		return true;
	}
}