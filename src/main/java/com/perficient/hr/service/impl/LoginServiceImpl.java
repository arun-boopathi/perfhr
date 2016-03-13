package com.perficient.hr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perficient.hr.dao.LoginDAO;
import com.perficient.hr.model.User;
import com.perficient.hr.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	 @Autowired
	 private LoginDAO loginDAO;

     public void setLoginDAO(LoginDAO loginDAO) {
          this.loginDAO = loginDAO;
     }
  
     public User checkLogin(String userName, String userPassword){
          return loginDAO.checkLogin(userName, userPassword);
     }
}