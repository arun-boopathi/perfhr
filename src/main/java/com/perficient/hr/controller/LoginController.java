package com.perficient.hr.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
			logger.debug("Authenticating User :"+loginForm.getUsername());
			User userExists = loginDao.checkLogin(loginForm.getUsername(),loginForm.getPassword());
			if(userExists != null){
				HttpSession session = request.getSession();
				session.setAttribute("userId", userExists.getEmployeePk());
//				logger.info("Authentication successful. Redirecting to home page.");
				model = new ModelAndView("redirect:/home");
			}else{
				model = new ModelAndView("login");
				model.addObject("msg", "Invalid UserName/Password!");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ModelAndView doLogut(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		session.invalidate();
//		logger.debug("User logged out successfully");
		ModelAndView model = new ModelAndView("login");
		model.addObject("msg", "Logged out Successfully!");
		return model;
	}
}
