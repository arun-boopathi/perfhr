package com.perficient.hr.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static final String SQL_DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATE_FORMAT = "dd-MM-yyyy";

	private DateUtils() {

	}

	public static SimpleDateFormat getiCalDateFormat() {
		return new SimpleDateFormat("yyyyMMdd'T'HHmm'00'");
	}

	public static Date getDateByddMMMyyyy(String date) throws ParseException {
		SimpleDateFormat ddMMMyyyyFormat = new SimpleDateFormat("dd-MMM-yyyy");
		return ddMMMyyyyFormat.parse(date);
	}

	/**
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 *             SimpleDateFormat isn't thread safe. Don't declare it as
	 *             static outside the method.
	 */
	public static Date getDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(SQL_DATE_FORMAT);
		return sdf.parse(date);
	}

	public static String convertMilliSecondsToStringDate(String milliSeconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(Long.parseLong(milliSeconds));
		SimpleDateFormat sdf = new SimpleDateFormat(SQL_DATE_FORMAT);
		return sdf.format(calendar.getTime());
	}

	public static Date convertMilliSecondsToDate(String milliSeconds) {
		return new Date(Long.parseLong(milliSeconds));
	}

	public static boolean isValidFormat(String value) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			date = sdf.parse(value);
			if (!value.equals(sdf.format(date))) {
				date = null;
			}
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return date != null;
	}
}
