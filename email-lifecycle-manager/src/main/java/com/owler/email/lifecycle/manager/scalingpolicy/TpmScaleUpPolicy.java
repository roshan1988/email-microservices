package com.owler.email.lifecycle.manager.scalingpolicy;

import java.util.Map;

public class TpmScaleUpPolicy implements ScalingPolicy {
	
	public static final String TPM_METRIC_KEY = "gauge.servo.tpm";
	
	int tpmScaleUpThreshold;
	
	public TpmScaleUpPolicy(int tpmScaleUpThreshold) {
		super();
		this.tpmScaleUpThreshold = tpmScaleUpThreshold;
	}

	public boolean execute(String serviceId, Map<?, ?> metrics){
		if(metrics.containsKey(TPM_METRIC_KEY)){
			Double tpm = (Double) metrics.get(TPM_METRIC_KEY);
			return (tpm > tpmScaleUpThreshold);
		}
		return false;
	}
	
}