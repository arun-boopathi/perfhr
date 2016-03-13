package com.perficient.hr.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.perficient.hr.dao.EmployeeDAO;
import com.perficient.hr.model.Employee;
import com.perficient.hr.model.User;

@Repository("employeeDAO")
public class EmployeeDAOImpl implements EmployeeDAO{

	@Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
       this.sessionFactory = sessionFactory;
    }
   
    protected Session getSession(){
       return sessionFactory.openSession();
    }
	
	public Employee loadEmployeeById(String employeeId) {
		Session session = sessionFactory.openSession();
		Employee employee = null;
		String SQL_QUERY =" from Employee as o where o.employee_id='"+employeeId+"'";
		Query query = session.createQuery(SQL_QUERY);
		List list = query.list();
		if ((list != null) && (list.size() > 0)) {
			employee = (Employee)list.get(0);
		}
		session.close();
		return employee;
	}

	public List<Employee> loadEmployees() {
		return null;
	}
}
