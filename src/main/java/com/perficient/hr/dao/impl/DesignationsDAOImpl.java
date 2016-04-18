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

import com.perficient.hr.dao.DesignationsDAO;
import com.perficient.hr.dao.EmployeeDAO;
import com.perficient.hr.model.Designations;
import com.perficient.hr.model.Employee;

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
	
	@SuppressWarnings("unchecked")
	public List<Designations> loadDesignations() {
	    Session session = sessionFactory.openSession();
		String SQL_QUERY = " from Designations";
		Query query = session.createQuery(SQL_QUERY);
		List<Designations> list = query.list();
		session.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	public boolean addDesignation(Designations designation) {
	    boolean returnVal = false;
		Session session = sessionFactory.openSession();
		try{
			Transaction tx = session.beginTransaction();
			session.merge(designation);
			tx.commit();
			returnVal = true;
		} catch(Exception e){
			logger.error("Unable to add designation: "+designation.getDesignation());
		} finally{
			session.close();	
		}
		return returnVal;
	}

}
