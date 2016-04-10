package com.perficient.hr.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.perficient.hr.dao.LoginDAO;
import com.perficient.hr.model.User;

@Repository("loginDAO")
public class LoginDAOImpl implements LoginDAO {
			 
	protected Logger logger = LoggerFactory.getLogger(LoginDAO.class);
	
    @Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
       this.sessionFactory = sessionFactory;
    }
  
    protected Session getSession(){
       return sessionFactory.openSession();
    }

    public User checkLogin(String userName, String userPassword){
		Session session = sessionFactory.openSession();
		User user = null;
		String SQL_QUERY =" from User as o where o.email_id=? and o.pwd=?";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter(0,userName);
		query.setParameter(1,userPassword);
		List list = query.list();
		if ((list != null) && (list.size() > 0)) {
			user = (User)list.get(0);
		}
		session.close();
		return user;              
    }
}