package com.perficient.hr.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.perficient.hr.model.Employee;
import com.perficient.hr.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value="/loadEmployee",method=RequestMethod.GET)
	@Produces("application/json")
	public @ResponseBody Employee loadEmployee(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		Employee employee = employeeService.loadEmployeeById(session.getAttribute("userId").toString());
		return employee;
	}
}
