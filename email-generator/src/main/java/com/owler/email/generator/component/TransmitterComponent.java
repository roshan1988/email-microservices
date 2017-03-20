package com.owler.email.generator.component;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.owler.email.generator.entity.EmailEntity;

@EnableFeignClients
@RefreshScope
@Component
public class TransmitterComponent {
	
	private static final Logger logger = LoggerFactory.getLogger(TransmitterComponent.class);

	public TransmitterComponent(){
		
	}
	
	@Autowired
	EmailTransmitterServiceProxy transmitterServiceProxy;
	
	@HystrixCommand(fallbackMethod = "logFailedEmailSend")
	public void sendEmailToTransmitter(List<EmailEntity> emailList) {
		for(EmailEntity emailEntity :emailList) {
			logger.info("Sending email for id : " + emailEntity.getId() + " To Address : " + emailEntity.getToAddress());
			transmitterServiceProxy.transmitEmail(emailEntity);
		}
	}
	
	public void logFailedEmailSend(List<EmailEntity> emailList) {
		logger.error("Error connecting to the email transmitter service");
	}
	
}

