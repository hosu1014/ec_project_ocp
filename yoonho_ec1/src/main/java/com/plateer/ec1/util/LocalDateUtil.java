package com.plateer.ec1.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateUtil {

	private final static String DATE_FORMAT = "yyyyMMdd";
	
	public static LocalDate of(String str) {
		return of(str, DATE_FORMAT);
	}
	
	public static LocalDate of(String str, String format) {
		DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern(format);
		return LocalDate.parse(str, formatDateTime);
	}
	
	
}
