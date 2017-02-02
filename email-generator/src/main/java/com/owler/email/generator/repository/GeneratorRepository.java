package com.owler.email.generator.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.owler.email.generator.entity.EmailRecord;

/**
 * @author Roshan Alexander
 *
 */
public interface GeneratorRepository extends JpaRepository<EmailRecord, Long> {
	
}
