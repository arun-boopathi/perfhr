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

import com.perficient.hr.dao.ProjectDAO;
import com.perficient.hr.model.Projects;

@Repository("projectDAO")
public class ProjectDAOImpl implements ProjectDAO{

	protected Logger logger = LoggerFactory.getLogger(ProjectDAOImpl.class);
	
	@Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
       this.sessionFactory = sessionFactory;
    }
    
    protected Session getSession(){
        return sessionFactory.openSession();
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Projects> loadProjects() {
		Session session = sessionFactory.openSession();
		String sqlQuery = " from Projects";
		Query query = session.createQuery(sqlQuery);
		List<Projects> list = query.list();
		session.close();
		return list;
	}

	@Override
	public boolean addProject(Projects project) {
		boolean returnVal = false;
		Session session = sessionFactory.openSession();
		try{
			Transaction tx = session.beginTransaction();
			session.save(project);
			tx.commit();
			returnVal = true;
		} catch(Exception e){
			logger.error("Unable to add designation: "+project.getProjectName()+" Exception is: "+e);
		} finally{
			session.close();	
		}
		return returnVal;
	}

	@Override
	public boolean updateProject(Projects project) {
		boolean returnVal = false;
		Session session = sessionFactory.openSession();
		try{
			Transaction tx = session.beginTransaction();
			session.merge(project);
			tx.commit();
			returnVal = true;
		} catch(Exception e){
			logger.error("Unable to update designation: "+project.getProjectName()+" Exception is: "+e);
		} finally{
			session.close();	
		}
		return returnVal;
	}
}
