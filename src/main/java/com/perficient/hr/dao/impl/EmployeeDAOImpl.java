package com.perficient.hr.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
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
		/*Transaction tx = session.beginTransaction();
		for(VW_Employee emp: list){
			EmployeeDesignation empDes = new EmployeeDesignation();
			empDes.setActive(PerfHrConstants.ACTIVE);
			empDes.setCreatedBy((long)1);
			empDes.setDesignationId(emp.getDesignations().getPk());
			empDes.setDtCreated(new Date());
			empDes.setDtModified(new Date());
			empDes.setEmployeeId(emp.getPk());
			empDes.setEndDate(null);
			empDes.setModifiedBy((long)1);
			empDes.setStartDate(new Date());
			session.save(empDes);
		}
		tx.commit();*/
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

	@SuppressWarnings("unchecked")
	@Override
	public List<VW_Employee> loadEmployeeByDesHistory(String stDate,
			String endDate, String desingation) {
		Session session = sessionFactory.openSession();
		/*String startDt = DateUtils.ConvertMilliSecondsToFormattedDate(stDate);
		String endDt = DateUtils.ConvertMilliSecondsToFormattedDate(endDate);*/
		String sqlQuery ="select e.firstName FROM VW_Employee e LEFT JOIN EmployeeDesignation s e.pk=s.employeeId and "
				+ "((s.startDate >= '2012-01-05' and s.startDate <= '2016-07-05') or "
				+ "((s.endDate >= '2012-01-05' and s.endDate <= '2016-07-05') "
				+ "or (s.startDate >= '2012-01-05' and s.startDate <= '2016-07-05' and s.endDate is null))) "
				+ "where s.designationId=4";
		Query query = session.createQuery(sqlQuery);
		List<VW_Employee> list = query.list();
		/*Criteria criteria = session.createCriteria(VW_Employee.class, "employee");
//		criteria.setFetchMode("EmployeeDesignation", FetchMode.JOIN);
//		criteria.createAlias("pk", "emp", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("employee.designations.pk", (long)4));
		List<VW_Employee> list = criteria.list();*/
		session.close();
		return list;
	}
}
