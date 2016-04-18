package com.perficient.hr.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.perficient.hr.dao.LoginDAO;
import com.perficient.hr.form.LoginForm;
import com.perficient.hr.model.User;

@Controller
public class LoginController {

	protected Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	public LoginDAO loginDao;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView doLogin(){
		ModelAndView model = new ModelAndView("login");
		return model;
	}

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("loginBean")LoginForm loginForm) {
		ModelAndView model = null;
		try {
			logger.info("Authenticating User :"+loginForm.getUsername());
			User userExists = loginDao.checkLogin(loginForm.getUsername(),loginForm.getPassword());
			if(userExists != null){
				HttpSession session = request.getSession();
				session.setAttribute("userId", userExists.getEmployeePk());
				logger.info("Authentication successful. Redirecting to home page.");
				model = new ModelAndView("redirect:/home");
			} else {
				logger.error("Invalid Credentials provided for User: "+loginForm.getUsername());
				model = new ModelAndView("login");
				model.addObject("msg", "Invalid UserName/Password!");
			}
		} catch(Exception e) {
			logger.error("Unable to Authenticate User: "+loginForm.getUsername());
		}
		return model;
	}
	
	@RequestMapping(value="/mobileLogin",method=RequestMethod.POST)
	@Produces("application/json")
	public @ResponseBody String loadEmployee(HttpServletRequest request, HttpServletResponse response, 
			@RequestHeader(value="username") String username, @RequestHeader(value="password") String password){
		String returnVal="failed";
		logger.info("Authenticating User :"+username);
		User userExists = loginDao.checkLogin(username, password);
		if(userExists != null){
			HttpSession session = request.getSession();
			session.setAttribute("userId", userExists.getEmployeePk());
			returnVal = "success";
			logger.info("Authentication successful");
		} else {
			logger.error("Invalid Credentials provided for User: "+password);
		}
		return returnVal;
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ModelAndView doLogut(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		session.invalidate();
		logger.info("User logged out successfully");
		ModelAndView model = new ModelAndView("login");
		model.addObject("msg", "Logged out Successfully!");
		return model;
	}
}
