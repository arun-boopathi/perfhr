package com.perficient.hr.controller;

import java.util.List;

import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.perficient.hr.dao.ProjectMembersDAO;
import com.perficient.hr.exception.RecordExistsException;
import com.perficient.hr.model.ProjectMembers;

@Controller
@RequestMapping("/v-projectmembers")
public class ProjectMembersController {

	@Autowired
	private ProjectMembersDAO projectMembersDAO;
	
	@RequestMapping(value="/loadProjectMembersByProjectId",method=RequestMethod.GET)
	@Produces("application/json")
	@ResponseBody
	public List<ProjectMembers> loadProjectMembersByProjectId(@RequestParam(value="projectId") String projectId){
		return projectMembersDAO.loadProjectMembersByProjectId(projectId);
	}
	
	@RequestMapping(value="/saveProjectMember", method=RequestMethod.POST)
	@Produces("application/json")
	@ResponseBody
	public ProjectMembers saveProjectMember(@RequestBody ProjectMembers projectMembers) throws RecordExistsException{
		return projectMembersDAO.saveProjectMember(projectMembers);
	}
	
}
