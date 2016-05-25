package com.perficient.hr.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

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
import com.perficient.hr.model.Designations;
import com.perficient.hr.model.Employee;
import com.perficient.hr.utils.PerfHrConstants;

@Repository("designationsDAO")
public class DesignationsDAOImpl implements DesignationsDAO {

	protected Logger logger = LoggerFactory.getLogger(DesignationsDAOImpl.class);
	
	@Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
       this.sessionFactory = sessionFactory;
    }
   
    protected Session getSession(){
       return sessionFactory.openSession();
    }
	
    @Autowired
    EmployeeDAO employeeDAO;
    
	@Override
	@SuppressWarnings("unchecked")
	public List<Designations> loadDesignations() {
	    Session session = sessionFactory.openSession();
		String sqlQuery = " from Designations";
		Query query = session.createQuery(sqlQuery);
		List<Designations> list = query.list();
		session.close();
		return list;
	}

	@Override
	public Designations addDesignation(Designations designation, String userId) {
		Designations returnVal = null;
		Session session = sessionFactory.openSession();
		try{
			Transaction tx = session.beginTransaction();
			Employee employee = employeeDAO.loadById(userId);
			designation.setDtCreated(new Date());
			designation.setDtModified(new Date());
			designation.setCreatedBy(employee.getPk());
			designation.setModifiedBy(employee.getPk());
			session.save(designation);
			tx.commit();
			returnVal = designation;
		} catch(Exception e){
			logger.error("Unable to add designation: "+designation.getDesignation()+" Exception is: "+e);
		} finally{
			session.close();	
		}
		return returnVal;
	}

	@Override
	public boolean updateDesignation(Designations designation) {
		boolean returnVal = false;
		Session session = sessionFactory.openSession();
		try{
			Transaction tx = session.beginTransaction();
			session.merge(designation);
			tx.commit();
			returnVal = true;
		} catch(Exception e){
			logger.error("Unable to update designation: "+designation.getDesignation()+" Exception is: "+e);
		} finally{
			session.close();	
		}
		return returnVal;
	}

	@Override
	public Designations loadDesignationById(String designationId) {
		Session session = sessionFactory.openSession();
		Designations desingation = (Designations)session.get(Designations.class, Long.parseLong(designationId));
		session.close();
		return desingation;
	}

	@Override
	public boolean deleteDesignation(Designations designation, String userId) {
		boolean returnVal = false;
		Session session = sessionFactory.openSession();
		try{
			Transaction tx = session.beginTransaction();
			designation.setActive(PerfHrConstants.INACTIVE);
			designation.setDtModified(new Date());
			designation.setModifiedBy(employeeDAO.loadById(userId).getPk());
			session.merge(designation);
			tx.commit();
			returnVal = true;
		} catch(Exception e){
			logger.error("Unable to update designation: "+designation.getDesignation()+" Exception is: "+e);
		} finally{
			session.close();	
		}
		return returnVal;
	}

}
