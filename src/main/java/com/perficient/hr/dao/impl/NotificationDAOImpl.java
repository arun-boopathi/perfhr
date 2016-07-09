package com.perficient.hr.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.perficient.hr.dao.NotificationDAO;
import com.perficient.hr.model.Employee;
import com.perficient.hr.model.Notification;
import com.perficient.hr.utils.PerfHrConstants;
import com.perficient.hr.utils.WsError;

@Repository("notificationDAO")
public class NotificationDAOImpl implements NotificationDAO{

protected Logger logger = LoggerFactory.getLogger(NotificationDAOImpl.class);
	
	@Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
       this.sessionFactory = sessionFactory;
    }
   
    protected Session getSession(){
       return sessionFactory.openSession();
    }
	
	@Override
	public boolean saveNotification(Notification notification) {
		boolean returnVal = false;
		Session session = sessionFactory.openSession();
		try{
			Transaction tx = session.beginTransaction();
			session.save(notification);
			tx.commit();
			returnVal = true;
		} catch(Exception e){
			logger.error("Unable to save notification: "+notification.getPk()+" Exception is: "+e);
		} finally{
			session.close();	
		}
		return returnVal;
	}
	
	@Override
	public boolean saveNotification(Notification notification, Session session) throws Exception {
		session.save(notification);
		return true;
	}

	@Override
	public boolean updateNotification(Notification notification) {
		boolean returnVal = false;
		Session session = sessionFactory.openSession();
		try{
			Transaction tx = session.beginTransaction();
			session.merge(notification);
			tx.commit();
			returnVal = true;
		} catch(Exception e){
			logger.error("Unable to update designation: "+notification.getPk()+" Exception is: "+e);
		} finally{
			session.close();	
		}
		return returnVal;
	}
	
	@Override
	public boolean updateNotification(Notification notification, Session session) {
		session.merge(notification);
		return true;
	}

	@Override
	public long getNotificationCount(String employeeId, Session session) throws Exception {
		String sqlQuery = "Select count(*) from Notification n where n.active=:active AND n.flag=:flag ";
		Query query = session.createQuery(sqlQuery);
		query.setParameter(PerfHrConstants.ACTIVE_COLUMN, PerfHrConstants.ACTIVE);
		query.setParameter("flag", PerfHrConstants.UNREAD);
		return  (long) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notification> loadNotificationsByGenericId(long genericId) {
		Session session = sessionFactory.openSession();
		String sqlQuery = " from Notification n WHERE n.active=:active AND n.idGeneric=:genericId";
		Query query = session.createQuery(sqlQuery);
		query.setParameter(PerfHrConstants.ACTIVE_COLUMN, PerfHrConstants.ACTIVE);
		query.setParameter(PerfHrConstants.GENERIC_ID_COLUMN, genericId);
		List<Notification> notificationList = query.list();
		session.close();
		return notificationList;
	}

	@Override
	public Object loadNotificationsToByGenericId(long genericId) {
		Session session = null;
		List<Employee> notificationToList = null;
		try {
			session = sessionFactory.openSession();
			notificationToList = loadNotificationsToByGenericId(genericId, session);
		} catch (Exception e) {
			logger.error("Unable to Load Notification: " + genericId + " Exception is: "+e);
			return new WsError("Unable to Load Notification: " + genericId + " Exception is: "+e, e);
		}
		finally	{
			session.close();
		}
		return notificationToList;
	}
	
	@Override
	public List<Employee> loadNotificationsToByGenericId(long genericId, Session session) throws Exception{
		String sqlQuery = "SELECT n.notificationTo from Notification n WHERE n.active=:active AND n.idGeneric=:genericId";
		Query query = session.createQuery(sqlQuery);
		query.setParameter(PerfHrConstants.ACTIVE_COLUMN, PerfHrConstants.ACTIVE);
		query.setParameter(PerfHrConstants.GENERIC_ID_COLUMN, genericId);
		@SuppressWarnings("unchecked")
		List<Employee> notificationToList = query.list();
		return notificationToList;
	}

	@Override
	public Notification loadByGenericAndEmployeeId(long genericId, long employeeId, boolean active) {
		Session session = sessionFactory.openSession();
		String sqlQuery = "from Notification n WHERE n.active=:active AND n.idGeneric=:genericId AND n.notificationTo.pk=:employeeId";
		Query query = session.createQuery(sqlQuery);
		query.setParameter(PerfHrConstants.ACTIVE_COLUMN, active);
		query.setParameter(PerfHrConstants.GENERIC_ID_COLUMN, genericId);
		query.setParameter("employeeId", employeeId);
		Notification notification = (Notification) query.uniqueResult();
		session.close();
		return notification;
	}

}
