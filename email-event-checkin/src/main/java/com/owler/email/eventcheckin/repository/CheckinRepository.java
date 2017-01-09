package com.owler.email.eventcheckin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.owler.email.eventcheckin.entity.CheckInRecord;

public interface CheckinRepository extends JpaRepository<CheckInRecord,Long> {

}
