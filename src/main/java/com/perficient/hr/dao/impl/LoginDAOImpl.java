package com.perficient.hr.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.perficient.hr.dao.LoginDAO;
import com.perficient.hr.model.User;

@Repository("loginDAO")
public class LoginDAOImpl implements LoginDAO{
			 
       @Resource(name="sessionFactory")
       protected SessionFactory sessionFactory;

       public void setSessionFactory(SessionFactory sessionFactory) {
          this.sessionFactory = sessionFactory;
       }
      
       protected Session getSession(){
          return sessionFactory.openSession();
       }

       public User checkLogin(String userName, String userPassword){
			System.out.println("In Check login");
			Session session = sessionFactory.openSession();
			User user = null;
			//Query using Hibernate Query Language
			String SQL_QUERY =" from User as o where o.login_id=? and o.password=?";
			Query query = session.createQuery(SQL_QUERY);
			query.setParameter(0,userName);
			query.setParameter(1,userPassword);
			List list = query.list();
			if ((list != null) && (list.size() > 0)) {
				System.out.println("user found!");
				user = (User)list.get(0);
			}
	
			session.close();
			return user;              
       }
}