package com.perficient.hr.dao;

import java.util.List;

import com.perficient.hr.model.Notification;

public interface NotificationDAO {

	public long getNotificationCount(String employeeId);
	
	public List<Notification> loadNotifications();
	
	public boolean saveNotification(Notification notification);
	
	public boolean updateNotification(Notification notification);
	
}
