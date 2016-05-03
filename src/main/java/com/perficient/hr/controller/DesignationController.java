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
import org.springframework.web.bind.annotation.ResponseBody;

import com.perficient.hr.dao.DesignationsDAO;
import com.perficient.hr.exception.RecordExistsException;
import com.perficient.hr.model.Designations;

@Controller
@RequestMapping("/v-designation")
public class DesignationController {

	protected Logger logger = LoggerFactory.getLogger(DesignationController.class);
	
	@Autowired
	private DesignationsDAO designationsDAO ;
	
	@RequestMapping(value="/loadDesignations",method=RequestMethod.GET)
	@Produces("application/json")
	@ResponseBody
	public List<Designations> loadDesignations(){
		return designationsDAO.loadDesignations();
	}
	
	@RequestMapping(value="/addDesignation", method=RequestMethod.POST)
	@Produces("application/json")
	@ResponseBody
	public Designations addDesignation(@RequestBody Designations designations) throws RecordExistsException{
		return designationsDAO.addDesignation(designations);
	}
	
	@RequestMapping(value="/updateDesignation", method=RequestMethod.PUT)
	@Produces("application/json")
	@ResponseBody
	public boolean updateDesignation(@RequestBody Designations designations) throws RecordExistsException{
		return designationsDAO.updateDesignation(designations);
	}
}
