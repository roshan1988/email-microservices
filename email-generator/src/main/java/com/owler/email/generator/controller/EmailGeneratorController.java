package com.owler.email.generator.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.owler.email.generator.component.GeneratorComponent;
import com.owler.email.generator.component.TransmitterComponent;
import com.owler.email.generator.entity.CheckInRecord;
import com.owler.email.generator.entity.EmailEntity;

@RestController
@CrossOrigin
@RequestMapping("/generator")
public class EmailGeneratorController {

	@Autowired
	GeneratorComponent generatorComponent;

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

	@RequestMapping(value = "/generate", method = RequestMethod.POST)
	long generateAndSendEmail(@RequestBody CheckInRecord checkInRecord) {
		List<EmailEntity> emailList = generatorComponent.generateEmail(checkInRecord);
		transmitterComponent.sendEmailToTransmitter(emailList);
		tpm.increment();
		return emailList.size();
	}

}
