package com.perficient.hr.utils;

import javax.servlet.http.HttpSession;

public class PerfUtils {

	private PerfUtils(){
		
	}
	
	public static String getUserId(HttpSession session){
		return session.getAttribute(PerfHrConstants.USER_ID).toString();
	}
}
