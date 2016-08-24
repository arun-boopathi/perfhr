package com.perficient.hr.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.perficient.hr.model.Projects;
import com.perficient.hr.utils.ValidationUtil;

public class ProjectValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return Projects.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Projects projects = (Projects) target;
		if (projects.getProjectName() == null || projects.getProjectName().trim().length() == 0) {
			errors.rejectValue("projectName", "Project Name is required");
		}
//		ValidationUtil.validateDate(projects.getStartDate());
		if (projects.getStartDate() == null || projects.getStartDate().toString().trim().length() == 0) {
			errors.rejectValue("startDate", "Start Date is required");
		}

		if (projects.getEndDate() == null || projects.getEndDate().toString().trim().length() == 0) {
			errors.rejectValue("endDate", "endDate.required", "End Date is required");
		}
	}
}
