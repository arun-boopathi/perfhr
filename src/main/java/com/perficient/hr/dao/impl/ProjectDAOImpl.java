package com.perficient.hr.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.perficient.hr.dao.EmployeeDAO;
import com.perficient.hr.dao.ProjectDAO;
import com.perficient.hr.model.Projects;
import com.perficient.hr.utils.PerfHrConstants;

@Repository("projectDAO")
public class ProjectDAOImpl implements ProjectDAO {

	protected Logger logger = LoggerFactory.getLogger(ProjectDAOImpl.class);
	
	@Autowired
    EmployeeDAO employeeDAO;
    
	@Override
	public Projects loadProjectById(String projectPk, Session session) throws  Exception{
		logger.info("Loading employee record for: "+projectPk);
		Projects projects = (Projects) session.get(Projects.class, Long.parseLong(projectPk));
		return projects;
	}
    
	@Override
	public List<Projects> loadProjects(Session session) throws  Exception {
		String sqlQuery = " from Projects p where p.active=:active";
		Query query = session.createQuery(sqlQuery);
		query.setParameter("active", PerfHrConstants.ACTIVE);
		@SuppressWarnings("unchecked")
		List<Projects> list = query.list();
		return list;
	}

	@Override
	public Projects addProject(Projects project, Session session) throws  Exception {
		session.save(project);
		return  project;
	}

	@Override
	public boolean updateProject(Projects project, Session session) throws  Exception {
		session.merge(project);
		return true;
	}

	@Override
	public boolean deleteProject(Projects project, Session session) throws  Exception {

		session.merge(project);
		
		String sqlQuery = "UPDATE ProjectMembers pm SET pm.active=:active WHERE pm.projectId.pk=:projectId";
		Query query = session.createQuery(sqlQuery);
		query.setParameter("active", PerfHrConstants.INACTIVE);
		query.setParameter("projectId", project.getPk());
		query.executeUpdate();
			
		return true;
	}
}