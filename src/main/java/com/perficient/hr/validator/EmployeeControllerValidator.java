package com.perficient.hr.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.perficient.hr.model.EmployeeModel;

public class EmployeeControllerValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return EmployeeModel.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.required");
		EmployeeModel emp = (EmployeeModel) target;
		if (emp.getFirstName().length() <= 0) {
			errors.rejectValue("firstName", "negativeValue", new Object[] { "'firstName'" }, "firstName cant be empty");
		}
		if (emp.getLastName().length() <= 0) {
			errors.rejectValue("lastName", "negativeValue", new Object[] { "'lastName'" }, "lastName cant be empty");
		}
	}

}
