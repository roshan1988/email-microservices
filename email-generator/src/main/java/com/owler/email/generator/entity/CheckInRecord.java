package com.owler.email.generator.entity;

import java.util.Date;

public class CheckInRecord {
	
	long id;
    
	long companyId;
    
    String eventType;
    
    Date checkInTime;
 
    public CheckInRecord(){
    	
    }
 	
	public CheckInRecord(long companyId, String eventType) {
		super();
		this.companyId = companyId;
		this.eventType = eventType;
	}

	@Override
	public String toString() {
		return "CheckInRecord [Event Id : " + id + ", Company Id = " + companyId + ", Event Type = " + eventType;
	}

	public Date getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
    
    
}
