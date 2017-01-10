package com.owler.email.transmitter.controller;

import javax.annotation.PostConstruct;

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
	
	@PostConstruct
	public void tpmRegister() {
		Runnable runnable = () -> {
			while (true) {
				gaugeService.submit("tpm", tpm.getTPM());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		Thread thread = new Thread(runnable);
		thread.start();
	}

	@RequestMapping(value="/transmit" , method = RequestMethod.POST)
	void generateAndSendEmail(@RequestBody EmailEntity emailEntity){
		tpm.increment();
		transmitterComponent.transmitEmail(emailEntity);
	}
	
}
