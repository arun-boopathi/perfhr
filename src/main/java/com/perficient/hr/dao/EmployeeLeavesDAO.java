package com.perficient.hr.dao;

import java.util.List;

import com.perficient.hr.model.EmployeeLeaves;

public interface EmployeeLeavesDAO {

	public boolean readPto(String fileName);
	
	public List<EmployeeLeaves> loadAllWfh();
	
	public EmployeeLeaves applyWfh(EmployeeLeaves employeeLeaves, String userId);
	
	public boolean updateWfh(EmployeeLeaves employeeLeaves, String userId);
	
	public boolean deleteWfh(EmployeeLeaves employeeLeaves, String userId);
	
}
