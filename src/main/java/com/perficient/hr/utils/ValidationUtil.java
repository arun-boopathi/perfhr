package com.perficient.hr.utils;

import java.util.regex.Pattern;

/**
 * 
 * @author d.venkatasubramanian
 *
 */
public class ValidationUtil {

	public ValidationUtil() {

	}

	public static String validate(String value, String type) {
		String errorMessage;
		switch (type) {
		case "text":
			errorMessage = validateText(value);
			break;
		case "date":
			errorMessage = validateDate(value);
			break;
		default:
			// Send mail to Development team
			errorMessage = "Some error occured";
		}
		return errorMessage;
	}

	public static String validateText(String value) {
		if (value == null) {
			return "Required";
		} else if (value.trim().length() == 0) {
			return "Length is undefined";
		} else if (!Pattern.matches(PerfHrConstants.TEXT_ONLY, value)) {
			return "Only text is allowed";
		} else {
			return null;
		}
	}

	public static String validateDate(String value) {
		String validateText = validateText(value);
		if (validateText != null) {
			return validateText;
		} else if (DateUtils.isValidFormat(value)) {
			return value + " is not a valid date";
		} else {
			return null;
		}
	}
}
