package com.perficient.hr.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.perficient.hr.dao.EmployeeRolesDAO;
import com.perficient.hr.dao.RolesDAO;
import com.perficient.hr.model.EmployeeRoles;
import com.perficient.hr.utils.PerfHrConstants;

@Repository("employeeRolesDAO")
public class EmployeeRolesDAOImpl implements EmployeeRolesDAO {

	protected Logger logger = LoggerFactory.getLogger(EmployeeRolesDAOImpl.class);
	
	@Autowired
    RolesDAO rolesDAO;
	
	@Override
	public EmployeeRoles saveEmpRoles(EmployeeRoles employeeRoles,
			Session session) {
		session.save(employeeRoles);
		return employeeRoles;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeRoles> loadEmpByRoles(String roleId, Session session) {
		String sqlQuery =" from EmployeeRoles r where r.active=:active and r.roleId=:roleId";
		Query query = session.createQuery(sqlQuery);
		query.setParameter(PerfHrConstants.ACTIVE_COLUMN, PerfHrConstants.ACTIVE);
		query.setParameter("roleId", rolesDAO.loadRolesById(roleId, session));
		return query.list();
	}
	
}
