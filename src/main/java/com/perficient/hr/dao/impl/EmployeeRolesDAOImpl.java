package com.perficient.hr.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.perficient.hr.dao.EmployeeRolesDAO;
import com.perficient.hr.dao.RolesDAO;
import com.perficient.hr.model.EmployeeRoles;
import com.perficient.hr.model.Roles;
import com.perficient.hr.utils.PerfHrConstants;

@Repository("employeeRolesDAO")
public class EmployeeRolesDAOImpl implements EmployeeRolesDAO {

	protected Logger logger = LoggerFactory.getLogger(EmployeeRolesDAOImpl.class);
	
	@Autowired
    RolesDAO rolesDAO;
	
	@Override
	public EmployeeRoles saveEmpRoles(EmployeeRoles employeeRoles, Session session) {
		try{
			getEmpRolesByRoleandEmployeeId(employeeRoles, session);
			session.merge(employeeRoles);
		} catch(IndexOutOfBoundsException iobe){
			session.save(employeeRoles);
		}
		return employeeRoles;
	}
	
	public EmployeeRoles getEmpRolesByRoleandEmployeeId(EmployeeRoles employeeRoles, Session session){
		Criteria criteria =  session.createCriteria(EmployeeRoles.class);
		criteria.add(Restrictions.eq("employee.pk", employeeRoles.getEmployee().getPk()));
		criteria.add(Restrictions.eq("roleId.pk", employeeRoles.getRoleId().getPk()));
		return (EmployeeRoles)criteria.list().get(0);
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

	@Override
	public int removeEmpRoles(Roles role, Session session) {
		String sqlQuery =" UPDATE EmployeeRoles r set r.active=:active where r.roleId=:roleId";
		Query query = session.createQuery(sqlQuery);
		query.setParameter(PerfHrConstants.ACTIVE_COLUMN, PerfHrConstants.INACTIVE);
		query.setParameter("roleId", rolesDAO.loadRolesById(String.valueOf(role.getPk()), session));
		return query.executeUpdate();
	}
	
}
