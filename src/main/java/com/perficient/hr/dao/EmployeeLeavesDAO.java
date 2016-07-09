package com.perficient.hr.dao;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.hibernate.Session;

import com.perficient.hr.model.EmployeeLeaves;

public interface EmployeeLeavesDAO {

	public List<EmployeeLeaves> loadAllLeaves(String leaveType, String calYear, Session session) throws Exception;
	
	public EmployeeLeaves loadLeaveById(String leaveId, Session session)throws Exception;
	
	public List<EmployeeLeaves> loadMyLeaves(String leaveType, String calYear, String employeeId, Session session)throws Exception;
	
	public EmployeeLeaves saveLeave(EmployeeLeaves employeeLeaves, Session session)throws Exception;
	
	public boolean updateLeave(EmployeeLeaves employeeLeaves, Session session)throws Exception;
	
	public Long getLeaveBalance(String leaveType, String calYear, String calMonth, String employeeId, 
			int totalLeaves, Session session)throws Exception;
	
	public List<EmployeeLeaves> loadLeaveReport(EmployeeLeaves employeeLeaves, Session session)throws Exception;

	public void saveLeave(Session session, Row row) throws Exception;

}
