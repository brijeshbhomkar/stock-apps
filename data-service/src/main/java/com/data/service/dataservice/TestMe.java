package com.data.service.dataservice;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(convertToDate("2016-10-03T00:00:00+0530"));
		
		String regex = "^([0-9]\\d{0,4})(\\.\\d{0,3})?$";
		Pattern pattern = Pattern.compile(regex);

		String x = "111444.22";
		Matcher matcher = pattern.matcher(x);
		System.out.println(matcher.find());
		
	}

	private static Date convertToDate(final String date) {
		LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ"));
		return java.sql.Timestamp.valueOf(localDateTime);
	}

	public static Date asDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
}
