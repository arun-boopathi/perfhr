package com.perficient.hr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perficient.hr.dao.EmployeeDAO;
import com.perficient.hr.model.Employee;
import com.perficient.hr.service.EmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeDAO employeeDAO;
	
	public void setLoginDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
   }
	
	public Employee loadEmployeeById(String employeeId) {
		return employeeDAO.loadEmployeeById(employeeId);
	}

	public List<Employee> loadEmployees() {
		return employeeDAO.loadEmployees();
	}

}
