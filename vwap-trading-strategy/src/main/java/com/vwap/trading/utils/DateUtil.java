package com.vwap.trading.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class DateUtil {

	static final SimpleDateFormat inputPattern = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
	static final SimpleDateFormat outputPattern = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public static String convertToDateString(final String date) {
		String output = null;
		try {
			output = outputPattern.format(inputPattern.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return output;
	}

	public static Date convertToDate(final String date) {
		Date output = null;

		String formattedDate;
		try {
			formattedDate = outputPattern.format(inputPattern.parse(date));
			output = java.sql.Timestamp.valueOf(formattedDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return output;
	}

	public static boolean tradingTime(final LocalTime currentTime) {
		final LocalTime startTime = LocalTime.of(9, 15);
		final LocalTime endTime = LocalTime.of(15, 30);
		return currentTime.isAfter(startTime) && currentTime.isBefore(endTime);
	}

}
