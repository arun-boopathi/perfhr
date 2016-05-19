package com.perficient.hr.model.type;

public enum Notificationtype {

	PTO("PTO"),
	UNPLANNED_PTO("Unplanned PTO"),
	WFH("WFH");
	
	private String notificationType;
	
	Notificationtype(String leaveType){
		this.notificationType = leaveType;
	}
	
	public String getLeaveType() {
        return notificationType;
    }
	
}
