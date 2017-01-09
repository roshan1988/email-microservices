package com.owler.email.lifecycle.manager.scalingpolicy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class  ScalingPolicies{
	
	@Value("${com.owler.email.lifecycle.manager.deploymentrules.tpm.scaleup.threshold}")
	private String tpmToScaleUp;
	
	public ScalingPolicy getPolicy(String serviceId){
		return new TpmScalingPolicy(Integer.parseInt(tpmToScaleUp));
	}
	
}