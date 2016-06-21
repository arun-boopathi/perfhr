package com.perficient.hr.model.type;

public enum MailStatusType {

	CONFIRMED("CONFIRMED"),
	REQUEST("REQUEST"),
	CANCEL("CANCEL"),
	CANCELLED("CANCELLED");
	
	private String mailStatusType;
	
	MailStatusType(String mailStatusType){
		this.mailStatusType = mailStatusType;
	}
	
	public String getMailStatusType() {
        return mailStatusType;
    }
	
}
