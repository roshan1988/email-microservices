package com.owler.email.eventcheckin.component;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.stereotype.Component;

import com.owler.email.eventcheckin.entity.CheckInRecord;
import com.owler.email.eventcheckin.repository.CheckinRepository;

@EnableFeignClients
@RefreshScope
@Component
public class CheckinComponent {
	private static final Logger logger = LoggerFactory.getLogger(CheckinComponent.class);

	@Autowired
	CheckinRepository checkinRepository;
	
	@Autowired
	EmailGeneratorServiceProxy generatorServiceProxy;

	public long checkIn(CheckInRecord checkIn) {
		checkIn.setCheckInTime(new Date());
		logger.info("Saving checkin event");
		long id = checkinRepository.save(checkIn).getId();
		logger.info("Successfully saved event checkin ");
		logger.info("Sending checkin event id for email generation : "+ id);
		generatorServiceProxy.generateEmailForEvent(checkIn);
		return id;
		
	}
	
	public CheckInRecord getCheckInRecord(long id){
		return checkinRepository.findOne(id);
	}
	
}	
