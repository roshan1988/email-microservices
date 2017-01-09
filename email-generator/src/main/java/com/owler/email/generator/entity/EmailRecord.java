package com.owler.email.generator.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmailRecord {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	long id;
    
    long eventId;
    
    String generationStatus;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public String getGenerationStatus() {
		return generationStatus;
	}

	public void setGenerationStatus(String generationStatus) {
		this.generationStatus = generationStatus;
	}
    
}
