package com.perficient.hr.dao;

import java.util.List;

import org.hibernate.Session;

import com.perficient.hr.model.Employee;
import com.perficient.hr.model.Notification;

public interface NotificationDAO {

	public long getNotificationCount(String employeeId);
	
	public List<Notification> loadNotificationsByGenericId(long genericId);
	
	public Object loadNotificationsToByGenericId(long genericId);
	
	public List<Employee> loadNotificationsToByGenericId(long genericId, Session session) throws Exception;
	
	public boolean saveNotification(Notification notification);
	
	public boolean saveNotification(Notification notification, Session session) throws Exception;
	
	public boolean updateNotification(Notification notification);
	
	public boolean updateNotification(Notification notification, Session session);
	
	public Notification loadByGenericAndEmployeeId(long genericId, long employeeId, boolean active);



	
}
