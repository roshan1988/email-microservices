package com.brownfied.pss.lcm.scalingpolicy;

import java.util.Map;

public class TpmScalingPolicy implements ScalingPolicy {
	public boolean execute(String serviceId, Map metrics){
		if(metrics.containsKey("gauge.servo.tpm")){
			Double tpm = (Double) metrics.get("gauge.servo.tpm");
			return (tpm > 2);
		}
		return false;
	}
}