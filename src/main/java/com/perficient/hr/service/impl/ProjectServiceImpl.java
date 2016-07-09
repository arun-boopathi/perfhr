package com.perficient.hr.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.perficient.hr.dao.EmployeeDAO;
import com.perficient.hr.dao.ProjectDAO;
import com.perficient.hr.model.Employee;
import com.perficient.hr.model.Projects;
import com.perficient.hr.service.ProjectService;
import com.perficient.hr.utils.ExceptionHandlingUtil;
import com.perficient.hr.utils.LoggerUtil;
import com.perficient.hr.utils.PerfHrConstants;

@Repository("projectService")
public class ProjectServiceImpl implements ProjectService {

	protected Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
	
	@Autowired
    EmployeeDAO employeeDAO;
	
	@Autowired
	ProjectDAO projectDAO;
    
	@Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
       this.sessionFactory = sessionFactory;
    }
    
    protected Session getSession(){
        return sessionFactory.openSession();
    }
 
	@Override
	public Object loadProjectById(String projectPk) {
		LoggerUtil.infoLog(logger, "Service to Load Project Details for: "+projectPk);
		Session session = null;
		try {
			session = sessionFactory.openSession();
			return  projectDAO.loadProjectById(projectPk, session);
		} catch (Exception e) {
			LoggerUtil.errorLog(logger, "Unable to Load Project Details for: "+projectPk , e);
			return ExceptionHandlingUtil.returnErrorObject("Unable to Load Project Details for: "+projectPk , e);
		}
		finally{
			ExceptionHandlingUtil.closeSession(session);
		}
	}
    
	@Override
	public Object loadProjects() {
		LoggerUtil.infoLog(logger, "Service to Load All Project.");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			return projectDAO.loadProjects(session);
		} catch (Exception e) {
			LoggerUtil.errorLog(logger, "Unable to Load All Project." , e);
			return ExceptionHandlingUtil.returnErrorObject("Unable to Load All Project." , e);
		}
		finally{
			ExceptionHandlingUtil.closeSession(session);
		}
		
	}

	@Override
	public Object addProject(Projects project, String userId) {
		LoggerUtil.infoLog(logger, "Service to Add New Project : "+project.getProjectName());
		Session session = null;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Employee employee = employeeDAO.loadById(userId, session);
			project.setDtCreated(new Date());
			project.setDtModified(new Date());
			project.setCreatedBy(employee.getPk());
			project.setModifiedBy(employee.getPk());
			projectDAO.addProject(project, session);
			tx.commit();
			return true;
		} catch(Exception e){
			LoggerUtil.errorLog(logger, "Unable to Add New Project : "+project.getProjectName(), e);
			ExceptionHandlingUtil.transactionRollback(tx);
			return ExceptionHandlingUtil.returnErrorObject("Unable to Add New Project : "+project.getProjectName(), e);
		} finally{
			ExceptionHandlingUtil.closeSession(session);	
		}
	}

	@Override
	public Object updateProject(Projects project, String userId) {
		LoggerUtil.infoLog(logger, "Service to Update  New Project : "+project.getProjectName());
		Session session = null;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Employee employee = employeeDAO.loadById(userId, session);
			project.setDtModified(new Date());
			project.setModifiedBy(employee.getPk());
			session.merge(project);
			tx.commit();
			return true;
		} catch(Exception e){
			LoggerUtil.errorLog(logger, "Unable to Update New Project : "+project.getProjectName(), e);
			ExceptionHandlingUtil.transactionRollback(tx);
			return ExceptionHandlingUtil.returnErrorObject("Unable to Update New Project : "+project.getProjectName(), e);
		} finally{
			ExceptionHandlingUtil.closeSession(session);	
		}
	}

	@Override
	public Object deleteProject(Projects project, String userId) {
		LoggerUtil.infoLog(logger, "Service to Delete New Project : "+project.getProjectName());
		Session session = null;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Employee employee = employeeDAO.loadById(userId, session);
			project.setActive(PerfHrConstants.INACTIVE);
			project.setDtModified(new Date());
			project.setModifiedBy(employee.getPk());
			
			projectDAO.deleteProject(project, session);
			
			tx.commit();
			return true;
		} catch(Exception e){
			LoggerUtil.errorLog(logger, "Unable to Delete New Project : "+project.getProjectName(), e);
			ExceptionHandlingUtil.transactionRollback(tx);
			return ExceptionHandlingUtil.returnErrorObject("Unable to Delete New Project : "+project.getProjectName(), e);
		} finally{
			ExceptionHandlingUtil.closeSession(session);	
		}
	}
}