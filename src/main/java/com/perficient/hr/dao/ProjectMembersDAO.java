package com.perficient.hr.dao;

import java.util.List;

import com.perficient.hr.model.ProjectMembers;

public interface ProjectMembersDAO {
	
	public List<ProjectMembers> loadAllProjectMembers();

	public List<ProjectMembers> loadProjectMembersByProjectId(String projectPk);
	
	public ProjectMembers loadProjectMemberById(String projectMemberId);
	
	public ProjectMembers saveProjectMember(ProjectMembers projectMembers, String userId);
	
	public boolean updateProjectMember(ProjectMembers projectMembers, String userId);
	
	public boolean deleteProjectMember(ProjectMembers projectMembers, String userId);
	
}
