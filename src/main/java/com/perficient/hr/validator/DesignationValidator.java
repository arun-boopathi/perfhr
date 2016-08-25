package com.perficient.hr.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.perficient.hr.model.Designations;
import com.perficient.hr.utils.ValidationUtil;

public class DesignationValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Designations.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Designations designations = (Designations) target;

		ValidationUtil validationUtil = new ValidationUtil();
		validationUtil.setErrors(errors);
		validationUtil.validateText("designation", designations.getDesignation());
		validationUtil.validateText("sbu", designations.getSbu());
		errors = validationUtil.getErrors();

	}

}
