package com.perficient.hr.model.type;

public enum LeaveType {

	PTO("PTO"),
	UNPLANNED_PTO("Unplanned PTO"),
	WFH("WFH"),
	ALL_PTO("ALL_PTO");
	
	private String leavesType;
	
	LeaveType(String leavesType){
		this.leavesType = leavesType;
	}
	
	public String getLeaveType() {
        return leavesType;
    }
	
}
