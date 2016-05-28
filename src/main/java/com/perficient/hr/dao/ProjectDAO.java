package com.perficient.hr.dao;

import java.util.List;

import com.perficient.hr.model.Projects;

public interface ProjectDAO {

    public List<Projects> loadProjects();
	
	public Projects addProject(Projects project, String userId);
	
	public boolean updateProject(Projects project, String userId);
	
	public boolean deleteProject(Projects project, String userId);
	
	public Projects loadProjectById(String projectPk);
	
}
