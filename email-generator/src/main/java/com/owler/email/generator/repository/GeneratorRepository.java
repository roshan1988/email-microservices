package com.owler.email.generator.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.owler.email.generator.entity.EmailRecord;

public interface GeneratorRepository extends JpaRepository<EmailRecord, Long> {
	
}
