package com.owler.email.generator.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.LongAdder;

import org.apache.commons.collections4.map.PassiveExpiringMap;

class TPMCounter {
	
	PassiveExpiringMap<Long, Date> transactionsCache = new PassiveExpiringMap<>(60000);

	LongAdder longAdder = new LongAdder();

	synchronized void increment() {
		longAdder.increment();
		transactionsCache.put(longAdder.longValue() , new Date());
	}
	
	synchronized int getTPM() {
		return transactionsCache.size();
	}

}