package com.owler.email.lifecycle.manager.deploymentrules;

public class MaxInstanceDeploymentRule implements DeploymentRule {

	private int maxInstance;
	
	private int currentInstance;

	public MaxInstanceDeploymentRule(int maxInstance, int currentInstance) {
		super();
		this.maxInstance = maxInstance;
		this.currentInstance = currentInstance;
	}

	public boolean execute() {
		if (maxInstance == 0) {
			return true;
		}
		if (currentInstance >= maxInstance) {
			return false;
		}
		return true;
	}
}