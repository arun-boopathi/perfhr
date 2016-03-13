package com.perficient.hr.service;

import java.util.List;

import com.perficient.hr.model.Employee;

public interface EmployeeService {

	public Employee loadEmployeeById(String employeeId);
	
	public List<Employee> loadEmployees();
	
}
