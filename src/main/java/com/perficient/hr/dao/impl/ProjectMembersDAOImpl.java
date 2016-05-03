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

import com.perficient.hr.dao.ProjectMembersDAO;
import com.perficient.hr.model.Designations;
import com.perficient.hr.model.ProjectMembers;

@Repository("projectMembersDAO")
public class ProjectMembersDAOImpl implements ProjectMembersDAO{

protected Logger logger = LoggerFactory.getLogger(ProjectMembersDAOImpl.class);
	
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
	public List<ProjectMembers> loadProjectMembersByProjectId(String projectPk) {
		Session session = sessionFactory.openSession();
		String sqlQuery = " from ProjectMembers as pm where pm.projectId=:projectPk";
		Query query = session.createQuery(sqlQuery);
		query.setParameter("projectPk", projectPk);
		List<ProjectMembers> list = query.list();
		session.close();
		return list;
	}

	@Override
	public ProjectMembers saveProjectMember(ProjectMembers projectMembers) {
		ProjectMembers returnVal = null;
		Session session = sessionFactory.openSession();
		try{
			Transaction tx = session.beginTransaction();
			session.save(projectMembers);
			tx.commit();
			returnVal = projectMembers;
		} catch(Exception e){
			logger.error("Unable to save project member: "+projectMembers.getEmployeeId()+" Exception is: "+e);
		} finally{
			session.close();	
		}
		return returnVal;
	}

	@Override
	public boolean deleteProjectMember(ProjectMembers projectMembers) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
