package com.perficient.hr.dao;

import java.util.List;

import com.perficient.hr.model.Projects;

public interface ProjectDAO {

    public List<Projects> loadProjects();
	
	public boolean addProject(Projects project);
	
	public boolean updateProject(Projects project);
	
}