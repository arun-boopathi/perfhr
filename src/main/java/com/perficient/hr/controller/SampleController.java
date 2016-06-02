package com.perficient.hr.controller;

import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.perficient.hr.model.EmailConstants;



@Controller
public class SampleController {
	
	@Autowired
	private EmailConstants emailConstants;
	
	@Value("${database.username}")
	private String web;
	
	
	@RequestMapping(value="/property",method=RequestMethod.GET)
	@Produces("application/json")
	@ResponseBody
		public void loadDesignations(){	
			System.out.println("Property : "+web);
			System.out.println("Property : "+emailConstants.getHost());
			System.out.println("Property : "+emailConstants.getPort());
			System.out.println("Property : "+emailConstants.getUsername());
			System.out.println("Property : "+emailConstants.getPassword());
	}
}
