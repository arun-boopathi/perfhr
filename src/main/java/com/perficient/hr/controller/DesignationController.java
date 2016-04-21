package com.perficient.hr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

import com.perficient.hr.dao.DesignationsDAO;
import com.perficient.hr.dao.impl.DesignationsDAOImpl;
import com.perficient.hr.exception.RecordExistsException;
import com.perficient.hr.exception.RecordNotFoundException;
import com.perficient.hr.model.Designations;
import com.perficient.hr.model.Employee;

@Controller
@RequestMapping("/v-designation")
public class DesignationController {

	protected Logger logger = LoggerFactory.getLogger(DesignationController.class);
	
	@Autowired
	private DesignationsDAO designationsDAO ;
	
	@RequestMapping(value="/loadDesignations",method=RequestMethod.GET)
	@Produces("application/json")
	public @ResponseBody List<Designations> loadDesignations(HttpServletRequest request, HttpServletResponse response){
		List<Designations> designations = designationsDAO.loadDesignations();
		return designations;
	}
	
	@RequestMapping(value="/addDesignation", method=RequestMethod.POST)
	@Produces("application/json")
	public @ResponseBody boolean addDesignation(HttpServletRequest request, HttpServletResponse response, @RequestBody Designations designations) throws RecordExistsException{
		boolean returnVal = false;
		designationsDAO.addDesignation(designations);
		return returnVal;
	}
	
	@RequestMapping(value="/updateDesignation", method=RequestMethod.POST)
	@Produces("application/json")
	public @ResponseBody boolean updateDesignation(HttpServletRequest request, HttpServletResponse response, @RequestBody Designations designations) throws RecordExistsException{
		boolean returnVal = false;
		designationsDAO.updateDesignation(designations);		
		return returnVal;
	}
}
