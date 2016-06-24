package com.perficient.hr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.perficient.hr.dao.ProjectMembersDAO;
import com.perficient.hr.exception.RecordExistsException;
import com.perficient.hr.exception.RecordNotFoundException;
import com.perficient.hr.model.ProjectMembers;
import com.perficient.hr.utils.PerfUtils;

@Controller
@RequestMapping("/v-projectmembers")
public class ProjectMembersController {

	@Autowired
	private ProjectMembersDAO projectMembersDAO;
	
	@RequestMapping(value="/loadAllProjectMembers",method=RequestMethod.GET)
	@Produces("application/json")
	@ResponseBody
	public List<ProjectMembers> loadAllProjectMembers(){
		return projectMembersDAO.loadAllProjectMembers();
	}
	
	@RequestMapping(value="/loadProjectMembersByProjectId",method=RequestMethod.GET)
	@Produces("application/json")
	@ResponseBody
	public List<ProjectMembers> loadProjectMembersByProjectId(@RequestParam(value="projectId") String projectId) {
		return projectMembersDAO.loadProjectMembersByProjectId(projectId);
	}
	
	@RequestMapping(value="/loadProjectMemberById", method=RequestMethod.GET)
	@Produces("application/json")
	@ResponseBody
	public ProjectMembers loadProjectMemberById(@RequestParam(value="projectMemberId") String projectMemberId) {
		return projectMembersDAO.loadProjectMemberById(projectMemberId);
	}
	
	@RequestMapping(value="/saveProjectMember", method=RequestMethod.POST)
	@Consumes("application/json")
	@Produces("application/json")
	@ResponseBody
	public ProjectMembers saveProjectMember(@ModelAttribute ProjectMembers projectMembers, HttpServletRequest request) throws RecordExistsException{
		return projectMembersDAO.saveProjectMember(projectMembers, PerfUtils.getUserId(request.getSession()));
	}
	
	@RequestMapping(value="/updateProjectMember", method=RequestMethod.PUT)
	@Produces("application/json")
	@ResponseBody
	public boolean updateProjectMember(@RequestBody ProjectMembers projectMembers, HttpServletRequest request) throws RecordNotFoundException {
		return projectMembersDAO.updateProjectMember(projectMembers, PerfUtils.getUserId(request.getSession()));
	}
	
	@RequestMapping(value="/deleteProjectMember", method=RequestMethod.PUT)
	@Produces("application/json")
	@ResponseBody
	public boolean deleteDesignation(@RequestBody ProjectMembers projectMembers, HttpServletRequest request) throws RecordNotFoundException{
		return projectMembersDAO.deleteProjectMember(projectMembers, PerfUtils.getUserId(request.getSession()));
	}
}
