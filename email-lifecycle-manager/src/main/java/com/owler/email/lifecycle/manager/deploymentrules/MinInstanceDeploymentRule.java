package com.owler.email.lifecycle.manager.deploymentrules;

public class MinInstanceDeploymentRule implements DeploymentRule {

	private int minInstance;
	
	private int currentInstance;

	public MinInstanceDeploymentRule(int minInstance, int currentInstance) {
		super();
		this.minInstance = minInstance;
		this.currentInstance = currentInstance;
	}

	public boolean execute() {
		if (minInstance == 0) {
			return true;
		}
		if (currentInstance <= minInstance) {
			return false;
		}
		return true;
	}
}