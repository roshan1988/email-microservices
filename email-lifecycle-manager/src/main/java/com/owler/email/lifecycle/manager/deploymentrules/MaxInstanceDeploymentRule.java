package com.owler.email.lifecycle.manager.deploymentrules;

import com.owler.email.lifecycle.manager.deploymentengine.DeploymentEngine;

public class MaxInstanceDeploymentRule implements DeploymentRule {

	private int max_instance;

	public MaxInstanceDeploymentRule(int max_instance) {
		super();
		this.max_instance = max_instance;
	}

	public boolean execute() {
		if (max_instance == 0) {
			return true;
		}
		if (DeploymentEngine.num_instances >= max_instance) {
			return false;
		}
		return true;
	}
}