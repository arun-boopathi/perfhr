package com.perficient.hr.model.type;

public enum NotificationStatusType {

	SUBMITTED("SUBMITTED"),
	PENDING("PENDING"),
	APPROVED("APPROVED"),
	REJECTED("REJECTED");
	
	private String notificationStatusType;
	
	NotificationStatusType(String notificationStatusType){
		this.notificationStatusType = notificationStatusType;
	}
	
	public String getNotificationStatusType() {
        return notificationStatusType;
    }
	
}
