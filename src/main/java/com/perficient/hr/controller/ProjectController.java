package com.perficient.hr.controller;

import java.util.List;

import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.perficient.hr.dao.ProjectDAO;
import com.perficient.hr.exception.RecordExistsException;
import com.perficient.hr.model.Projects;

@Controller
@RequestMapping("/v-projects")
public class ProjectController {

protected Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@Autowired
	private ProjectDAO projectDAO;
	
	@RequestMapping(value="/loadProjects",method=RequestMethod.GET)
	@Produces("application/json")
	@ResponseBody
	public List<Projects> loadProjects(){
		return projectDAO.loadProjects();
	}
	
	@RequestMapping(value="/loadProjectById",method=RequestMethod.GET)
	@Produces("application/json")
	@ResponseBody
	public Projects loadProjectById(@RequestParam(value="projectPk") String projectPk){
		return projectDAO.loadProjectById(projectPk);
	}
	
	@RequestMapping(value="/addProject", method=RequestMethod.POST)
	@Produces("application/json")
	@ResponseBody
	public Projects addDesignation(@RequestBody Projects project) throws RecordExistsException{
		return projectDAO.addProject(project);
	}
	
	@RequestMapping(value="/updateProject", method=RequestMethod.PUT)
	@Produces("application/json")
	@ResponseBody
	public boolean updateDesignation(@RequestBody Projects project) throws RecordExistsException{
		return projectDAO.updateProject(project);
	}
}
