package com.owler.email.transmitter.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import com.owler.email.transmitter.entity.EmailEntity;

@RefreshScope
@Component
public class TransmitterComponent {
	
	private static final Logger logger = LoggerFactory.getLogger(TransmitterComponent.class);

	public TransmitterComponent(){
		
	}
	
	public void transmitEmail(EmailEntity emailEntity) {
		logger.info("Transmitting Email : " + emailEntity.getId());
	}
	
}

