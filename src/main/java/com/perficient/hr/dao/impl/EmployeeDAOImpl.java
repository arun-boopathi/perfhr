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
	
	@SuppressWarnings("unchecked")
	public Employee loadEmployeeById(String employeePk) {
		logger.info("Loading employee record for: "+employeePk);
		Session session = sessionFactory.openSession();
		Employee employee = null;
		String SQL_QUERY =" from Employee as o where o.pk='"+employeePk+"'";
		Query query = session.createQuery(SQL_QUERY);
		List<Employee> list = query.list();
		if ((list != null) && (list.size() > 0)) {
			employee = (Employee)list.get(0);
		}
		session.close();
		return employee;
	}

	@SuppressWarnings("unchecked")
	public List<Employee> loadEmployees() {
		Session session = sessionFactory.openSession();
		String SQL_QUERY =" from Employee";
		Query query = session.createQuery(SQL_QUERY);
		List<Employee> list = query.list();
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
			logger.error("Unable to update employee: "+employee.getEmployee_id());
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
			logger.error("Unable to save employee: "+employee.getEmail());
		} finally{
			session.close();	
		}
		return returnVal;
	}
}
