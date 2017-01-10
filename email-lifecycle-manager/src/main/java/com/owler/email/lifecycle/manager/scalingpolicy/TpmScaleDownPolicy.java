package com.owler.email.lifecycle.manager.scalingpolicy;

import java.util.Map;

public class TpmScaleDownPolicy implements ScalingPolicy {
	
	public static final String TPM_METRIC_KEY = "gauge.servo.tpm";
	
	int tpmScaleDownThreshold;
	
	public TpmScaleDownPolicy(int tpmScaleDownThreshold) {
		super();
		this.tpmScaleDownThreshold = tpmScaleDownThreshold;
	}

	public boolean execute(String serviceId, Map<?, ?> metrics){
		if(metrics.containsKey(TPM_METRIC_KEY)){
			Double tpm = (Double) metrics.get(TPM_METRIC_KEY);
			return (tpm < tpmScaleDownThreshold);
		}
		return false;
	}
	
}