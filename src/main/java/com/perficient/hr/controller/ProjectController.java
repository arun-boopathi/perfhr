package com.perficient.hr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.perficient.hr.dao.ProjectDAO;

@Controller
@RequestMapping("/v-projects")
public class ProjectController {

protected Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@Autowired
	private ProjectDAO projectDAO;
	
	/*@RequestMapping(value="/loadProjects",method=RequestMethod.GET)
	@Produces("application/json")
	@ResponseBody
	public List<Projects> loadProjects(){
		return projectDAO.loadProjects();
	}
	
	@RequestMapping(value="/addProject", method=RequestMethod.POST)
	@Produces("application/json")
	@ResponseBody
	public boolean addDesignation(@RequestBody Projects project) throws RecordExistsException{
		return projectDAO.addProject(project);
	}
	
	@RequestMapping(value="/updateProject", method=RequestMethod.PUT)
	@Produces("application/json")
	@ResponseBody
	public boolean updateDesignation(@RequestBody Projects project) throws RecordExistsException{
		return projectDAO.updateProject(project);
	}
	*/
}
