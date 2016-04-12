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

import com.perficient.hr.dao.EmployeeDAO;
import com.perficient.hr.exception.RecordNotFoundException;
import com.perficient.hr.model.Employee;

@Controller
@RequestMapping("/v-employee")
public class EmployeeController {

	@Autowired
	private EmployeeDAO employeeDAO;
	
	@RequestMapping(value="/loadEmployee",method=RequestMethod.GET)
	@Produces("application/json")
	public @ResponseBody Employee loadEmployee(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		Employee employee = employeeDAO.loadEmployeeById(session.getAttribute("userId").toString());
		return employee;
	}
	
	@RequestMapping(value="/loadEmployeeById",method=RequestMethod.GET)
	@Produces("application/json")
	public @ResponseBody Employee loadEmployeeById(@RequestParam(value="employeeId") String employeeId, HttpServletRequest request, HttpServletResponse response){
		Employee employee = employeeDAO.loadEmployeeById(employeeId);
		return employee;
	}
	
	@RequestMapping(value="/loadAllEmployee",method=RequestMethod.GET)
	@Produces("application/json")
	public @ResponseBody List<Employee> loadAllEmployee(HttpServletRequest request, HttpServletResponse response){
		List<Employee> employees = employeeDAO.loadEmployees();
		return employees;
	}
	
	@RequestMapping(value="/updateEmployee", method=RequestMethod.PUT)
	@Produces("application/json")
	public @ResponseBody boolean updateEmployee(HttpServletRequest request, HttpServletResponse response, @RequestBody Employee employee) throws RecordNotFoundException{
		boolean returnVal = false;
		if(employeeDAO.loadEmployeeById(String.valueOf(employee.getPk())) == null){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			throw new RecordNotFoundException();
		} else {
			returnVal = employeeDAO.updateEmployee(employee);
		}
		return returnVal;
	}
}
