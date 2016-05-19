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
import com.perficient.hr.exception.RecordExistsException;
import com.perficient.hr.exception.RecordNotFoundException;
import com.perficient.hr.model.Employee;

@Controller
@RequestMapping("/v-employee")
public class EmployeeController {

	@Autowired
	private EmployeeDAO employeeDAO;
	
	@RequestMapping(value="/loadEmployee",method=RequestMethod.GET)
	@Produces("application/json")
	@ResponseBody
	public Employee loadEmployee(HttpServletRequest request){
		HttpSession session = request.getSession();
		return employeeDAO.loadById(session.getAttribute("userId").toString());
	}
	
	@RequestMapping(value="/loadEmployeeById",method=RequestMethod.GET)
	@Produces("application/json")
	@ResponseBody
	public Employee loadEmployeeById(@RequestParam(value="employeeId") String employeeId){
		return employeeDAO.loadById(employeeId);
	}
	
	@RequestMapping(value="/loadAllEmployee",method=RequestMethod.GET)
	@Produces("application/json")
	@ResponseBody
	public List<Employee> loadAllEmployee(){
		return employeeDAO.loadEmployees();
	}
	
	@RequestMapping(value="/updateEmployee", method=RequestMethod.PUT)
	@Produces("application/json")
	@ResponseBody
	public boolean updateEmployee(HttpServletResponse response, @RequestBody Employee employee) throws RecordNotFoundException{
		if(employeeDAO.loadById(String.valueOf(employee.getPk())) == null){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			throw new RecordNotFoundException();
		}
		return employeeDAO.updateEmployee(employee);
	}
	
	@RequestMapping(value="/addEmployee", method=RequestMethod.POST)
	@Produces("application/json")
	@ResponseBody
	public boolean addEmployee(@RequestBody Employee employee, HttpServletResponse response) throws RecordExistsException{
		boolean returnVal = false;
		if(!employeeDAO.addEmployee(employee)){
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			throw new RecordExistsException();
		}
		return returnVal;
	}
}