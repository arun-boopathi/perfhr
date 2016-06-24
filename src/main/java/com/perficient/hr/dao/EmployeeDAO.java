package com.perficient.hr.dao;

import java.util.List;

import com.perficient.hr.model.Employee;
import com.perficient.hr.model.EmployeeView;

public interface EmployeeDAO {

	public EmployeeView loadByUserId(String employeeId);
	
	public Employee loadById(String employeeId);
	
	public List<EmployeeView> loadEmployees();
	
	public boolean updateEmployee(Employee employee, String userId);
	
	public boolean addEmployee(Employee employee);
	
	public List<EmployeeView> loadEmployeeByDesHistory(String stDate, String endDate, String desingation); 
}
