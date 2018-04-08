package com.shane.stryde.webservice.utils;

import java.time.Clock;
import java.time.LocalDateTime;

public class DateUtils {

	public static LocalDateTime getLocalDateTimeUtc() {
		return LocalDateTime.now(Clock.systemUTC());
	}
}
