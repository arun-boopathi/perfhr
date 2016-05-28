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
import com.perficient.hr.dao.ProjectDAO;
import com.perficient.hr.dao.ProjectMembersDAO;
import com.perficient.hr.model.Employee;
import com.perficient.hr.model.ProjectMembers;
import com.perficient.hr.model.Projects;
import com.perficient.hr.utils.PerfHrConstants;

@Repository("projectDAO")
public class ProjectDAOImpl implements ProjectDAO {

	protected Logger logger = LoggerFactory.getLogger(ProjectDAOImpl.class);
	
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
    
    @Autowired
	private ProjectMembersDAO projectMembersDAO;
    
	@Override
	public Projects loadProjectById(String projectPk) {
		logger.info("Loading employee record for: "+projectPk);
		Session session = sessionFactory.openSession();
		Projects projects = (Projects) session.get(Projects.class, Long.parseLong(projectPk));
		session.close();
		return projects;
	}
    
	@SuppressWarnings("unchecked")
	@Override
	public List<Projects> loadProjects() {
		Session session = sessionFactory.openSession();
		String sqlQuery = " from Projects p where p.active=:active";
		Query query = session.createQuery(sqlQuery);
		query.setParameter("active", PerfHrConstants.ACTIVE);
		List<Projects> list = query.list();
		session.close();
		return list;
	}

	@Override
	public Projects addProject(Projects project, String userId) {
		Projects returnVal = null;
		Session session = sessionFactory.openSession();
		try{
			Transaction tx = session.beginTransaction();
			Employee employee = employeeDAO.loadById(userId);
			project.setDtCreated(new Date());
			project.setDtModified(new Date());
			project.setCreatedBy(employee.getPk());
			project.setModifiedBy(employee.getPk());
			session.save(project);
			tx.commit();
			returnVal = project;
		} catch(Exception e){
			logger.error("Unable to add designation: "+project.getProjectName()+" Exception is: "+e);
		} finally{
			session.close();	
		}
		return returnVal;
	}

	@Override
	public boolean updateProject(Projects project, String userId) {
		boolean returnVal = false;
		Session session = sessionFactory.openSession();
		try{
			Transaction tx = session.beginTransaction();
			Employee employee = employeeDAO.loadById(userId);
			project.setDtModified(new Date());
			project.setModifiedBy(employee.getPk());
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

	@Override
	public boolean deleteProject(Projects project, String userId) {
		boolean returnVal = false;
		Session session = sessionFactory.openSession();
		try{
			Transaction tx = session.beginTransaction();
			Employee employee = employeeDAO.loadById(userId);
			project.setActive(PerfHrConstants.INACTIVE);
			project.setDtModified(new Date());
			project.setModifiedBy(employee.getPk());
			session.merge(project);
			
			String sqlQuery = "UPDATE ProjectMembers pm SET pm.active=:active WHERE pm.projectId.pk=:projectId";
			Query query = session.createQuery(sqlQuery);
			query.setParameter("active", PerfHrConstants.INACTIVE);
			query.setParameter("projectId", project.getPk());
			query.executeUpdate();
			
			tx.commit();
			returnVal = true;
		} catch(Exception e){
			logger.error("Unable to delete project: "+project.getProjectName()+" Exception is: "+e);
		} finally{
			session.close();	
		}
		return returnVal;
	}
}