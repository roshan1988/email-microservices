package com.owler.email.lifecycle.manager.scalingpolicy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class  ScalingPolicies{
	
	@Value("${com.owler.email.lifecycle.manager.deploymentrules.tpm.scaleup.threshold}")
	private String tpmToScaleUp;
	
	@Value("${com.owler.email.lifecycle.manager.deploymentrules.tpm.scaledown.threshold}")
	private String tpmToScaleDown;
	
	public ScalingPolicy getScaleUpPolicy(String serviceId){
		return new TpmScaleUpPolicy(Integer.parseInt(tpmToScaleUp));
	}
	
	public ScalingPolicy getScaleDownPolicy(String serviceId){
		return new TpmScaleDownPolicy(Integer.parseInt(tpmToScaleDown));
	}
	
}