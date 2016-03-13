package com.perficient.hr.dao;

import com.perficient.hr.model.User;

public interface LoginDAO{    
       public User checkLogin(String userName, String userPassword);
}