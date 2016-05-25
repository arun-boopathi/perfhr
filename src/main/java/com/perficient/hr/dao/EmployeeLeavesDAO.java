package com.perficient.hr.dao;

import java.util.List;

import com.perficient.hr.model.EmployeeLeaves;

public interface EmployeeLeavesDAO {

	public boolean pasrsePtoDocument(String fileName);
	
	public List<EmployeeLeaves> loadAllLeaves(String leaveType);
	
	public EmployeeLeaves loadLeaveById(String leaveId);
	
	public List<EmployeeLeaves> loadMyLeaves(String leaveType, String employeeId);
	
	public EmployeeLeaves applyLeave(EmployeeLeaves employeeLeaves, String userId);
	
	public boolean updateLeave(EmployeeLeaves employeeLeaves, String userId);
	
	public boolean deleteLeave(EmployeeLeaves employeeLeaves, String userId);
	
}
