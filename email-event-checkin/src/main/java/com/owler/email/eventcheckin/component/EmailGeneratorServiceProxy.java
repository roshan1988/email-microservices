package com.owler.email.eventcheckin.component;

import org.springframework.cloud.netflix.feign.FeignClient;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.owler.email.eventcheckin.entity.CheckInRecord;

@FeignClient(name="email-generator-service")
public interface EmailGeneratorServiceProxy {
	
	@RequestMapping(value = "generator/generate", method=RequestMethod.POST)
	void generateEmailForEvent(CheckInRecord checkInRecord);
}