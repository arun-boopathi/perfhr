package com.perficient.hr.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.perficient.hr.dao.EmployeeDAO;
import com.perficient.hr.dao.EmployeeRolesDAO;
import com.perficient.hr.form.EmployeeRolesForm;
import com.perficient.hr.model.Employee;
import com.perficient.hr.model.EmployeeRoles;
import com.perficient.hr.model.EmployeeView;
import com.perficient.hr.service.EmployeeRolesService;
import com.perficient.hr.utils.ExceptionHandlingUtil;
import com.perficient.hr.utils.LoggerUtil;

@Repository("employeeRolesService")
public class EmployeeRolesServiceImpl implements EmployeeRolesService{

protected Logger logger = LoggerFactory.getLogger(EmployeeRolesServiceImpl.class);
	
	@Autowired
    EmployeeDAO employeeDAO;
	
	@Autowired
    EmployeeRolesDAO employeeRolesDAO;
	
	@Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
       this.sessionFactory = sessionFactory;
    }
   
    protected Session getSession(){
       return sessionFactory.openSession();
    }
	
	@Override
	public Object saveEmpRoles(EmployeeRolesForm empRolesForm, String userId) {
		LoggerUtil.infoLog(logger, "Add Designation Service Started");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Employee employee = employeeDAO.loadById(userId, session);
			EmployeeRoles empRoles = new EmployeeRoles();
			empRoles.setDtCreated(new Date());
			empRoles.setDtModified(new Date());
			empRoles.setCreatedBy(employee.getPk());
			empRoles.setModifiedBy(employee.getPk());
			empRoles.setRoleId(empRolesForm.getRole());
			for(Employee emp: empRolesForm.getEmployee()){
				empRoles.setEmployee(emp);
				employeeRolesDAO.saveEmpRoles(empRoles, session);
			}
			tx.commit();
			return empRolesForm;
		} catch(Exception e){
			LoggerUtil.errorLog(logger, "Unable to add employee roles: "+empRolesForm.getRole(), e);
			ExceptionHandlingUtil.transactionRollback(tx);
			return ExceptionHandlingUtil.returnErrorObject("Unable to add designation: "+empRolesForm.getRole(), e);
		} finally {
			ExceptionHandlingUtil.closeSession(session);	
		}
	}

	@Override
	public Object loadEmpByRoles(String roleId, String userId) {
		LoggerUtil.infoLog(logger, "Load All employee list.");
		Session session = null;
		List<EmployeeRoles> list = null;
		try {
			session = sessionFactory.openSession();
			list = employeeRolesDAO.loadEmpByRoles(roleId, session);
		} catch (Exception e) {
			LoggerUtil.errorLog(logger, "Unable to load employee List ", e);
			return ExceptionHandlingUtil.returnErrorObject("Unable to load employee List ", e); 
		} finally {
			ExceptionHandlingUtil.closeSession(session);
		}
		return list;
	}
}