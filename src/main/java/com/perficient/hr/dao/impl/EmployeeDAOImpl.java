package com.perficient.hr.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.perficient.hr.dao.DesignationsDAO;
import com.perficient.hr.dao.EmployeeDAO;
import com.perficient.hr.model.Employee;
import com.perficient.hr.model.EmployeeView;
import com.perficient.hr.utils.DateUtils;
import com.perficient.hr.utils.PerfHrConstants;
import com.perficient.hr.utils.WsError;

@Repository("employeeDAO")
public class EmployeeDAOImpl implements EmployeeDAO{

	protected Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);

    @Autowired
	private DesignationsDAO designationsDAO;
	
	@Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
       this.sessionFactory = sessionFactory;
    }
   
    protected Session getSession(){
       return sessionFactory.openSession();
    }
	
    @Override
    public EmployeeView loadByUserId(String pk) {
		logger.info("Loading employee record for: "+pk);
		Session session = sessionFactory.openSession();
		String sqlQuery =" from EmployeeView as o where o.pk=:pk and o.active=:active";
		Query query = session.createQuery(sqlQuery);
		query.setParameter("pk", Long.parseLong(pk));
		query.setParameter(PerfHrConstants.ACTIVE_COLUMN, PerfHrConstants.ACTIVE);
		EmployeeView employee = (EmployeeView)query.uniqueResult();
		session.close();
		return employee;
	}

    @Override
	public Object loadById(String pk) {
		logger.info("Loading employee record for: "+pk);
		Session session = null;
		Employee employee = null;
		try {
			session = sessionFactory.openSession();
			employee = loadById(pk, session);;
		} catch (Exception e) {
			logger.error("Unable to get employee: "+pk +" Exception is: "+e);
			return new WsError("Unable to get employee: "+pk +" Exception is: "+e, e);
		}
		finally
		{
			session.close();
		}
		return employee;
	}
    
    @Override
   	public Employee loadById(String pk, Session session) throws Exception{
   		logger.info("Loading employee record for: "+pk);
   		String sqlQuery =" from Employee as o where o.pk=:pk and o.active=:active";
   		Query query = session.createQuery(sqlQuery);
   		query.setParameter("pk", Long.parseLong(pk));
   		query.setParameter(PerfHrConstants.ACTIVE_COLUMN, PerfHrConstants.ACTIVE);
   		Employee employee = (Employee)query.uniqueResult();
   		return employee;
   	}
    
    @Override
	@SuppressWarnings("unchecked")
	public List<EmployeeView> loadEmployees() {
		Session session = sessionFactory.openSession();
		String sqlQuery =" from EmployeeView e where e.active=:active order by e.firstName asc";
		Query query = session.createQuery(sqlQuery);
		query.setParameter(PerfHrConstants.ACTIVE_COLUMN, PerfHrConstants.ACTIVE);
		List<EmployeeView> list = query.list();
		session.close();
		return list;
	}

	@Override
	public boolean updateEmployee(Employee employee, String userId) {
		boolean returnVal = false;
		Session session = sessionFactory.openSession();
		try{
			Transaction tx = session.beginTransaction();
			employee.setModifiedBy(loadById(userId, session).getPk());
			employee.setDtModified(new Date());
			session.merge(employee);
			tx.commit();
			returnVal = true;
		} catch(Exception e){
			logger.error("Unable to update employee: "+employee.getEmployeeId()+" Exception is: "+e);
		} finally{
			session.close();	
		}
		return returnVal;
	}

	@Override
	public boolean addEmployee(Employee employee) {
		boolean returnVal = false;
		Session session = sessionFactory.openSession();
		try{
			Transaction tx = session.beginTransaction();
			session.save(employee);
			tx.commit();
			returnVal = true;
		} catch(Exception e){
			logger.error("Unable to save employee: "+employee.getEmail()+" Exception is: "+e);
		} finally{
			session.close();	
		}
		return returnVal;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeView> loadEmployeeByDesHistory(String stDate,
			String endDate, String designationName) {
		Session session = sessionFactory.openSession();
		Query query = session.createSQLQuery(
				"CALL getEmployeeDesignationByRange(:stDate, :endDate, :designationName)")
				.addEntity(EmployeeView.class)
				.setParameter("stDate", DateUtils.convertMilliSecondsToDate(stDate))
				.setParameter("endDate", DateUtils.convertMilliSecondsToDate(endDate))
				.setParameter("designationName", designationName);
		List<EmployeeView> list = query.list();
		session.close();
		return list;
	}
}
