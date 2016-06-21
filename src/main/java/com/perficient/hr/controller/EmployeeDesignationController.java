package com.perficient.hr.controller;

import java.util.List;

import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.perficient.hr.dao.EmployeeDesignationDAO;
import com.perficient.hr.form.JobTitle;

@Controller
@RequestMapping("/v-reportJobTitle")
public class EmployeeDesignationController {

	@Autowired
	private EmployeeDesignationDAO employeeDesignationDAO;
	
	@RequestMapping(value="/loadBySbu/{fromDate}/{toDate}/{sbu}/{designation}",method=RequestMethod.GET)
	@Produces("application/json")
	@ResponseBody
	public List<JobTitle> loadBySbu(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate, 
			@PathVariable("sbu") String sbu, @PathVariable("designation") String designation){
		return employeeDesignationDAO.loadBySbu(fromDate, toDate, sbu, designation);
	}
	
}
