package com.perficient.hr.dao;

import java.text.ParseException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.hibernate.Session;

import com.perficient.hr.model.EmployeeLeaves;

public interface EmployeeLeavesDAO {

	public List<EmployeeLeaves> loadAllLeaves(String leaveType, String calYear, Session session) throws ParseException;
	
	public EmployeeLeaves loadLeaveById(String leaveId, Session session);
	
	public List<EmployeeLeaves> loadMyLeaves(String leaveType, String calYear, String employeeId, Session session) throws ParseException;
	
	public EmployeeLeaves saveLeave(EmployeeLeaves employeeLeaves, Session session);
	
	public boolean updateLeave(EmployeeLeaves employeeLeaves, Session session);
	
	public Long getLeaveBalance(String leaveType, String calYear, String calMonth, String employeeId, 
			int totalLeaves, Session session) throws ParseException;
	
	public List<EmployeeLeaves> loadLeaveReport(EmployeeLeaves employeeLeaves, Session session);

	public void saveLeave(Session session, Row row) throws ParseException;

}
