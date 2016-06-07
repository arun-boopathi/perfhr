package com.perficient.hr.dao;

import java.util.List;

import com.perficient.hr.model.EmployeeLeaves;

public interface EmployeeLeavesDAO {

	public boolean pasrsePtoDocument(String fileName);
	
	public List<EmployeeLeaves> loadAllLeaves(String leaveType, String calYear);
	
	public EmployeeLeaves loadLeaveById(String leaveId);
	
	public List<EmployeeLeaves> loadMyLeaves(String leaveType, String calYear, String employeeId);
	
	public EmployeeLeaves applyLeave(EmployeeLeaves employeeLeaves, String userId);
	
	public boolean updateLeave(EmployeeLeaves employeeLeaves, String userId);
	
	public boolean deleteLeave(EmployeeLeaves employeeLeaves, String userId);
	
	public Long getLeaveBalance(String leaveType, String calYear, String employeeId, int totalLeaves);
	
}
