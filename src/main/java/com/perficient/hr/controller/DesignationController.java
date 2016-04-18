package com.perficient.hr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.perficient.hr.dao.DesignationsDAO;
import com.perficient.hr.exception.RecordNotFoundException;
import com.perficient.hr.model.Designations;
import com.perficient.hr.model.Employee;

@Controller
@RequestMapping("/v-designation")
public class DesignationController {

	
	@Autowired
	private DesignationsDAO designationsDAO ;
	
	@RequestMapping(value="/loadDesignations",method=RequestMethod.GET)
	@Produces("application/json")
	public @ResponseBody List<Designations> loadDesignations(HttpServletRequest request, HttpServletResponse response){
		List<Designations> designations = designationsDAO.loadDesignations();
		return designations;
	}
	
	@RequestMapping(value="", method=RequestMethod.PUT)
	@Produces("application/json")
	public @ResponseBody boolean addDesignation(HttpServletRequest request, HttpServletResponse response, @RequestBody Designations designations) throws RecordNotFoundException{
		boolean returnVal = false;
		
			returnVal = designationsDAO.addDesignation(designations);
		
		return returnVal;
	}
}
