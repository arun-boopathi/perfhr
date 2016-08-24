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
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "designation",
		// "designation.required");
		Designations designations = (Designations) target;
		
		ValidationUtil.validateText(designations.getDesignation());
		
		ValidationUtil.validateText(designations.getSbu());
		

		if (designations.getDesignation() == null || designations.getDesignation().trim().length() == 0) {
			errors.rejectValue("designation", "designation.required", "Designation is required");
		}
		if (designations.getSbu() == null || designations.getSbu().trim().length() == 0) {
			errors.rejectValue("sbu", "sbu.required", "SBU is required");
		}
	}

}
