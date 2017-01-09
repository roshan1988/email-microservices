package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import com.owler.email.transmitter.EmailTransmitterApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EmailTransmitterApplication.class)
@WebAppConfiguration
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}

}
