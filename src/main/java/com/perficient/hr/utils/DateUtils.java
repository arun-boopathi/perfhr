package com.perficient.hr.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static final String SQL_DATE_FORMAT = "yyyy-MM-dd";
	
	
	public static Date getDate(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(SQL_DATE_FORMAT);
		return sdf.parse(date);
	}
	
}
