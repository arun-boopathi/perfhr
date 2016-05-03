package com.perficient.hr.dao;

import java.util.List;

import com.perficient.hr.model.ProjectMembers;

public interface ProjectMembersDAO {

	public List<ProjectMembers> loadProjectMembersByProjectId(String projectPk);
	
	public ProjectMembers saveProjectMember(ProjectMembers projectMembers);
	
	public boolean deleteProjectMember(ProjectMembers projectMembers);
	
}
