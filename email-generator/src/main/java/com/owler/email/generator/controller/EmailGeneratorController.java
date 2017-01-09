package com.owler.email.generator.controller;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

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

	@RequestMapping(value = "/generate", method = RequestMethod.POST)
	long generateAndSendEmail(@RequestBody CheckInRecord checkInRecord) {
		List<EmailEntity> emailList = generatorComponent.generateEmail(checkInRecord);
		transmitterComponent.sendEmailToTransmitter(emailList);
		tpm.increment();
		gaugeService.submit("tpm", tpm.count.intValue());
		return emailList.size();
	}

	class TPMCounter {
		LongAdder count;
		Calendar expiry = null;

		TPMCounter() {
			reset();
		}

		void reset() {
			count = new LongAdder();
			expiry = Calendar.getInstance();
			expiry.add(Calendar.MINUTE, 1);
		}

		boolean isExpired() {
			return Calendar.getInstance().after(expiry);
		}

		void increment() {
			if (isExpired()) {
				reset();
			}
			count.increment();
		}

	}

}
