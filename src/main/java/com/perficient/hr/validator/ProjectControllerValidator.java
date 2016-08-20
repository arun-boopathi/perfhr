package com.perficient.hr.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.perficient.hr.model.Projects;
import com.perficient.hr.utils.DateUtils;

public class ProjectControllerValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return Projects.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Projects projects = (Projects) target;
		if (projects.getProjectName() == null || projects.getProjectName().trim().length() == 0) {
			errors.rejectValue("projectName", "projectName.required", "Project Name is required");
		}
		if (projects.getStartDate() == null || projects.getStartDate().toString().trim().length() == 0) {
			errors.rejectValue("startDate", "startDate.required", "Start Date is required");
		}

		if (projects.getEndDate() == null || projects.getEndDate().toString().trim().length() == 0) {
			errors.rejectValue("endDate", "endDate.required", "End Date is required");
		}
	}
}
