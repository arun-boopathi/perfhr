package com.perficient.hr.dao;

import java.util.List;

import com.perficient.hr.model.Employee;

public interface EmployeeDAO {

	public Employee loadEmployeeById(String employeeId);
	
	public List<Employee> loadEmployees();
	
	public boolean updateEmployee(Employee employee);
	
	public boolean addEmployee(Employee employee);
}
