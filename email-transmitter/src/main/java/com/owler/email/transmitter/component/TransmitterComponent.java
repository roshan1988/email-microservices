package com.owler.email.transmitter.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.owler.email.transmitter.entity.EmailEntity;

@RefreshScope
@Component
public class TransmitterComponent {

	private static final Logger logger = LoggerFactory.getLogger(TransmitterComponent.class);

	@Autowired
	MailSender mailSender;

	public void transmitEmail(EmailEntity emailEntity) {
		logger.info("Transmitting Email : " + emailEntity.getId());
		SimpleMailMessage message = new SimpleMailMessage();
		message.setText(emailEntity.getContent());
		message.setTo(emailEntity.getToAddress());
		message.setFrom("owlerinsightl@owler.com");
		try {
			mailSender.send(message);
		} catch (Exception e) {
			logger.error("Error sending mail with id : " + emailEntity.getId(), e);
		}
	}

}
