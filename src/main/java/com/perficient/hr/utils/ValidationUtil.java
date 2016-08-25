package com.perficient.hr.utils;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

/**
 * 
 * @author d.venkatasubramanian
 *
 */
public class ValidationUtil {
	Errors errors;

	public void validateText(String field, String value) {
		ValidationUtils.rejectIfEmptyOrWhitespace(this.errors, field, "Required");
		if (this.errors.getFieldError(field) != null) {
			if (!Pattern.matches(PerfHrConstants.TEXT_ONLY, value)) {
				errors.rejectValue(field, "Only text is allowed");
			}
		}
	}

	public void validateDate(String field, String value) {
		validateText(field, value);
		if (this.errors.getFieldError(field) != null) {
			if (DateUtils.isValidDate(value)) {
				errors.rejectValue(field, "Date is invalid");
			}
		}
	}

	public Errors getErrors() {
		return errors;
	}

	public void setErrors(Errors errors) {
		this.errors = errors;
	}
}
