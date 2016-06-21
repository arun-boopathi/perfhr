package com.perficient.hr.service;

import com.perficient.hr.form.NotificationMail;

public interface MailService {

	void sendNotifcationMail(NotificationMail notificationMail) throws Exception;
	
}
