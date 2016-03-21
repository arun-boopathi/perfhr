package com.perficient.hr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value="/loadEmployeeById",method=RequestMethod.GET)
	@Produces("application/json")
	public @ResponseBody Employee loadEmployeeById(@RequestParam(value="employeeId") String employeeId, HttpServletRequest request, HttpServletResponse response){
		Employee employee = employeeService.loadEmployeeById(employeeId);
		return employee;
	}
	
	@RequestMapping(value="/loadAllEmployee",method=RequestMethod.GET)
	@Produces("application/json")
	public @ResponseBody List<Employee> loadAllEmployee(HttpServletRequest request, HttpServletResponse response){
		List<Employee> employees = employeeService.loadEmployees();
		return employees;
	}
}
