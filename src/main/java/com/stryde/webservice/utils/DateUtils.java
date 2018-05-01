package com.stryde.webservice.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.time.LocalDateTime;

public class DateUtils {

	private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

	public static LocalDateTime getLocalDateTimeUtc() {
		return LocalDateTime.now(Clock.systemUTC());
	}

	//Format: DDMMYY
	public static String localDateTimeToDateString(LocalDateTime datetime) throws IllegalArgumentException {

		if(datetime==null|| datetime.equals(null)){
			throw new IllegalArgumentException("LocalDateTime format is fucked");
		}

		String day = Integer.toString(datetime.getDayOfMonth());
		String month;
		if(datetime.getMonth().getValue()<10){
			month = "0" + Integer.toString(datetime.getMonth().getValue());
		}else{
			month = Integer.toString(datetime.getMonth().getValue());
		}
		String year = Integer.toString(datetime.getYear());


		return day + month + year;
	}
}
