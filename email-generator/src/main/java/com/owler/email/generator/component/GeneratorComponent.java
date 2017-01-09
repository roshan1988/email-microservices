package com.owler.email.generator.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import com.owler.email.generator.entity.CheckInRecord;
import com.owler.email.generator.entity.EmailEntity;
import com.owler.email.generator.entity.EmailRecord;
import com.owler.email.generator.repository.GeneratorRepository;

@RefreshScope
@Component
public class GeneratorComponent {
	
	@Autowired
	GeneratorRepository generatorRepository;
	
	public List<EmailEntity> generateEmail(CheckInRecord checkInRecord) {
		List<EmailEntity> emailList = new ArrayList<>();
		EmailEntity emailEntity = new EmailEntity();
		emailEntity.setContent("Email for Event : " + checkInRecord.getEventType() + " for Company : " + checkInRecord.getCompanyId());
		emailEntity.setSubject(checkInRecord.getEventType() + " : " + checkInRecord.getCompanyId() + " Event Date : " + checkInRecord.getCheckInTime());
		emailEntity.setToAddress("ralexander@owler.com");
		EmailRecord emailRecord = new EmailRecord();
		emailRecord.setEventId(checkInRecord.getId());
		emailRecord.setGenerationStatus("GENERATION_SUCESS");
		emailRecord = generatorRepository.saveAndFlush(emailRecord);
		emailEntity.setId(emailRecord.getId());
		emailList.add(emailEntity);
		return emailList;
	}
	
}

