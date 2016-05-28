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

import com.perficient.hr.dao.EmployeeDAO;
import com.perficient.hr.dao.ProjectMembersDAO;
import com.perficient.hr.model.Employee;
import com.perficient.hr.model.ProjectMembers;
import com.perficient.hr.utils.PerfHrConstants;

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
    
    @Autowired
    EmployeeDAO employeeDAO;
	
    @SuppressWarnings("unchecked")
	@Override
	public List<ProjectMembers> loadAllProjectMembers() {
    	List<ProjectMembers> projectMembers = null;
    	Session session = sessionFactory.openSession();
		String sqlQuery = " from ProjectMembers pm where pm.active=:active";
		Query query = session.createQuery(sqlQuery);
		query.setParameter("active", PerfHrConstants.ACTIVE);
		projectMembers = query.list();
		session.close();
		return projectMembers;
	}
	
    
    @SuppressWarnings("unchecked")
	@Override
	public List<ProjectMembers> loadProjectMembersByProjectId(String projectPk) {
		Session session = sessionFactory.openSession();
		String sqlQuery = " from ProjectMembers as pm where pm.projectId.pk=:projectPk";
		Query query = session.createQuery(sqlQuery);
		query.setParameter("projectPk", Long.parseLong(projectPk));
		List<ProjectMembers> list = query.list();
		session.close();
		return list;
	}

	@Override
	public ProjectMembers saveProjectMember(ProjectMembers projectMembers, String userId) {
		ProjectMembers returnVal = null;
		Session session = sessionFactory.openSession();
		try{
			Transaction tx = session.beginTransaction();
			Employee employee = employeeDAO.loadById(userId);
			projectMembers.setDtCreated(new Date());
			projectMembers.setDtModified(new Date());
			projectMembers.setCreatedBy(employee.getPk());
			projectMembers.setModifiedBy(employee.getPk());
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
	public boolean deleteProjectMember(ProjectMembers projectMembers,
			String userId) {
		boolean returnVal = false;
		Session session = sessionFactory.openSession();
		try{
			Transaction tx = session.beginTransaction();
			projectMembers.setActive(PerfHrConstants.INACTIVE);
			projectMembers.setDtModified(new Date());
			projectMembers.setModifiedBy(employeeDAO.loadById(userId).getPk());
			session.merge(projectMembers);
			tx.commit();
			returnVal = true;
		} catch(Exception e){
			logger.error("Unable to update project Members: "+projectMembers.getPk()+" Exception is: "+e);
		} finally{
			session.close();	
		}
		return returnVal;
	}

	@Override
	public ProjectMembers loadProjectMemberById(String projectMemberId) {
		Session session = sessionFactory.openSession();
		ProjectMembers projectMember = (ProjectMembers)session.get(ProjectMembers.class, Long.parseLong(projectMemberId));
		session.close();
		return projectMember;
	}

	@Override
	public boolean updateProjectMember(ProjectMembers projectMembers,
			String userId) {
		boolean returnVal = false;
		Session session = sessionFactory.openSession();
		try{
			Transaction tx = session.beginTransaction();
			projectMembers.setDtModified(new Date());
			projectMembers.setModifiedBy(employeeDAO.loadById(userId).getPk());
			session.merge(projectMembers);
			tx.commit();
			returnVal = true;
		} catch(Exception e){
			logger.error("Unable to update project Members: "+projectMembers.getPk()+" Exception is: "+e);
		} finally{
			session.close();	
		}
		return returnVal;
	}
	
}
