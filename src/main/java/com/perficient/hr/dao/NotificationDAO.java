package com.perficient.hr.dao;

import java.util.List;

import com.perficient.hr.model.Employee;
import com.perficient.hr.model.Notification;

public interface NotificationDAO {

	public long getNotificationCount(String employeeId);
	
	public List<Notification> loadNotificationsByGenericId(long genericId);
	
	public List<Employee> loadNotificationsToByGenericId(long genericId);
	
	public boolean saveNotification(Notification notification);
	
	public boolean updateNotification(Notification notification);
	
	public Notification loadByGenericAndEmployeeId(long genericId, long employeeId, int active);
	
}
