package com.perficient.hr.model.type;

public enum LeaveType {

	PTO("PTO"),
	UNPLANNED_PTO("Unplanned PTO"),
	WFH("WFH");
	
	private String leaveType;
	
	LeaveType(String leaveType){
		this.leaveType = leaveType;
	}
	
	public String getLeaveType() {
        return leaveType;
    }
	
}
