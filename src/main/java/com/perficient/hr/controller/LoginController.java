package com.perficient.hr.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.perficient.hr.form.LoginForm;
import com.perficient.hr.model.User;
import com.perficient.hr.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	public LoginService loginService;
	
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
			System.out.println("user "+loginForm.getUsername());
			User userExists = loginService.checkLogin(loginForm.getUsername(),loginForm.getPassword());
			if(userExists != null){
				HttpSession session = request.getSession();
				session.setAttribute("userId", userExists.getEmployeePk());
				model = new ModelAndView("redirect:home");
			}else{
				model = new ModelAndView("login");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return model;
	}
}
