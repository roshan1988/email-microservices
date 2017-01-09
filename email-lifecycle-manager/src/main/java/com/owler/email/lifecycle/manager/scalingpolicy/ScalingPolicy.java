package com.owler.email.lifecycle.manager.scalingpolicy;

import java.util.Map;

public interface ScalingPolicy{
	public boolean execute(String serviceId, Map metrics);
}