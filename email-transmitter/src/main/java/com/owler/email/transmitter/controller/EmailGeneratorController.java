package com.owler.email.transmitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.owler.email.transmitter.component.TransmitterComponent;
import com.owler.email.transmitter.entity.EmailEntity;

@RestController
@CrossOrigin
@RequestMapping("/transmitter")
public class EmailGeneratorController {
	
	@Autowired
	TransmitterComponent transmitterComponent;
	
	@Autowired
	GaugeService gaugeService;

	TPMCounter tpm = new TPMCounter();

	@RequestMapping(value="/transmit" , method = RequestMethod.POST)
	void generateAndSendEmail(@RequestBody EmailEntity emailEntity){
		tpm.increment();
		gaugeService.submit("tpm", tpm.getTPM());
		transmitterComponent.transmitEmail(emailEntity);
	}
	
}
