package com.owler.email.transmitter.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.owler.email.transmitter.entity.EmailRecord;

public interface TransmitterRepository extends JpaRepository<EmailRecord, Long> {
	
}
