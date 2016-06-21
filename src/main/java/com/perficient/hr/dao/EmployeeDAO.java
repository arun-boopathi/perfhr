package com.perficient.hr.dao;

import java.util.List;

import com.perficient.hr.model.Employee;
import com.perficient.hr.model.VW_Employee;

public interface EmployeeDAO {

	public VW_Employee loadByUserId(String employeeId);
	
	public Employee loadById(String employeeId);
	
	public List<VW_Employee> loadEmployees();
	
	public boolean updateEmployee(Employee employee);
	
	public boolean addEmployee(Employee employee);
	
	public List<VW_Employee> loadEmployeeByDesHistory(String stDate, String endDate, String desingation); 
}
