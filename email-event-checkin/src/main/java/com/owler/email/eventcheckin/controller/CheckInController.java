package com.owler.email.eventcheckin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.owler.email.eventcheckin.component.CheckinComponent;
import com.owler.email.eventcheckin.entity.CheckInRecord;

@RestController
@CrossOrigin
@RequestMapping("/eventcheckin")
public class CheckInController {

	CheckinComponent checkInComponent;
	
	@Autowired
	CheckInController(CheckinComponent checkInComponent){
		this.checkInComponent = checkInComponent;
	}
	
	@RequestMapping("/get/{id}")
	CheckInRecord getCheckIn(@PathVariable long id ){
		return checkInComponent.getCheckInRecord(id);
	}

	@RequestMapping(value ="/checkin", method = RequestMethod.POST)
	long checkIn(@RequestBody CheckInRecord checkIn){
		return checkInComponent.checkIn(checkIn);
	}
	
}
