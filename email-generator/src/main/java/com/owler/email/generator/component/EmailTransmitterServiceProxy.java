package com.owler.email.generator.component;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.owler.email.generator.entity.EmailEntity;

@FeignClient(name="email-transmitter-service")
public interface EmailTransmitterServiceProxy {
	
	@RequestMapping(value = "transmitter/transmit", method=RequestMethod.POST)
	void transmitEmail(@RequestBody EmailEntity emailEntity);
}