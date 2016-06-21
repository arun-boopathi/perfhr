package com.perficient.hr.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static final String SQL_DATE_FORMAT = "yyyy-MM-dd";
	private static SimpleDateFormat sdf = new SimpleDateFormat(SQL_DATE_FORMAT);
	
	public static Date getDate(String date) throws ParseException{
		return sdf.parse(date);
	}

    public static String ConvertMilliSecondsToFormattedDate(String milliSeconds){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(milliSeconds));
        return sdf.format(calendar.getTime());
    }
}
