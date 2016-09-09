package com.perficient.hr.dao;

import java.util.List;

import org.hibernate.Session;

import com.perficient.hr.model.EmployeeRoles;
import com.perficient.hr.model.EmployeeView;

public interface EmployeeRolesDAO {

	public EmployeeRoles saveEmpRoles(EmployeeRoles employeeRoles, Session session);
	
	public List<EmployeeRoles> loadEmpByRoles(String roleId, Session session);
	
}
