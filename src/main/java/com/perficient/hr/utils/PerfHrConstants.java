package com.perficient.hr.utils;

public class PerfHrConstants {

	public static final boolean ACTIVE = false;
	public static final boolean INACTIVE = true;

	public static final int READ = 1;
	public static final int UNREAD = 0;

	public static final String FIRST_HALF = "FIRST";
	public static final String SECOND_HALF = "SECOND";

	public static final int WORK_HOURS = 8;
	public static final float LEAVE_PER_MONTH = (float) 1.67;

	public static final String USER_ID = "userId";

	public static final String LOGIN_MODEL = "login";

	public static final String ACTIVE_COLUMN = "active";
	public static final String GENERIC_ID_COLUMN = "genericId";

	public static final String TEXT_ONLY = "^[a-zA-Z ]*$";
	public static final String NUMBER_ONLY = "^[0-9]+$";
	public static final String EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String DATE = "^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";;
	public static final String ALPHA_NUMBERIC = "^[a-zA-Z0-9]*$";

	private PerfHrConstants() {

	}
}