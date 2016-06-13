package com.perficient.hr.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.perficient.hr.dao.EmployeeDAO;
import com.perficient.hr.model.Employee;
import com.perficient.hr.model.VW_Employee;
import com.perficient.hr.utils.PerfHrConstants;

@Repository("employeeDAO")
public class EmployeeDAOImpl implements EmployeeDAO{

	protected Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);
	
	@Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
       this.sessionFactory = sessionFactory;
    }
   
    protected Session getSession(){
       return sessionFactory.openSession();
    }
	
    public VW_Employee loadByUserId(String pk) {
		logger.info("Loading employee record for: "+pk);
		Session session = sessionFactory.openSession();
		VW_Employee employee = null;
		String sqlQuery =" from VW_Employee as o where o.pk=:pk";
		Query query = session.createQuery(sqlQuery);
		query.setParameter("pk", Long.parseLong(pk));
		employee = (VW_Employee)query.uniqueResult();
		session.close();
		return employee;
	}

    @Override
	public Employee loadById(String pk) {
		logger.info("Loading employee record for: "+pk);
		Session session = sessionFactory.openSession();
		Employee employee = null;
		String sqlQuery =" from Employee as o where o.pk=:pk";
		Query query = session.createQuery(sqlQuery);
		query.setParameter("pk", Long.parseLong(pk));
		employee = (Employee)query.uniqueResult();
		session.close();
		return employee;
	}
    
    @Override
	@SuppressWarnings("unchecked")
	public List<VW_Employee> loadEmployees() {
		Session session = sessionFactory.openSession();
		String sqlQuery =" from VW_Employee e where e.active=:active order by e.firstName asc";
		Query query = session.createQuery(sqlQuery);
		query.setParameter("active", PerfHrConstants.ACTIVE);
		List<VW_Employee> list = query.list();
		session.close();
		return list;
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		boolean returnVal = false;
		Session session = sessionFactory.openSession();
		try{
			Transaction tx = session.beginTransaction();
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
}
