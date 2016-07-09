package com.perficient.hr.dao;

import java.util.List;

import org.hibernate.Session;

import com.perficient.hr.model.Employee;
import com.perficient.hr.model.EmployeeView;

public interface EmployeeDAO {

	public EmployeeView loadByUserId(String employeeId, Session session) throws Exception;
	
	public Employee loadById(String pk, Session session) throws Exception;
	
	public List<EmployeeView> loadEmployees(Session session) throws Exception;
	
	public boolean updateEmployee(Employee employee, String userId, Session session) throws Exception;
	
	public boolean addEmployee(Employee employee, Session session) throws Exception;
	
	public List<EmployeeView> loadEmployeeByDesHistory(String stDate, String endDate, String desingation, Session session) throws Exception;

}
