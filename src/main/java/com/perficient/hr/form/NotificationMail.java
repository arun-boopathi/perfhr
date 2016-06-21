package com.perficient.hr.form;

import java.util.Date;
import java.util.List;

public class NotificationMail {
	
	private List<String> recipientsList;

	private String subject;
	
	private String msgBody;
	
	private boolean isCalendar;
	
	private Date DT_START;
	
	private Date DT_END;
	
	private String description;
	
	private String summary;
	
	private String requestType;
	
	private String statusType;
	
	private String sequence;

	/**
	 * @return the recipientsList
	 */
	public List<String> getRecipientsList() {
		return recipientsList;
	}

	/**
	 * @param recipientsList the recipientsList to set
	 */
	public void setRecipientsList(List<String> recipientsList) {
		this.recipientsList = recipientsList;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the msgBody
	 */
	public String getMsgBody() {
		return msgBody;
	}

	/**
	 * @param msgBody the msgBody to set
	 */
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

	/**
	 * @return the isCalendar
	 */
	public boolean isCalendar() {
		return isCalendar;
	}

	/**
	 * @param isCalendar the isCalendar to set
	 */
	public void setCalendar(boolean isCalendar) {
		this.isCalendar = isCalendar;
	}

	/**
	 * @return the dT_START
	 */
	public Date getDT_START() {
		return DT_START;
	}

	/**
	 * @param dT_START the dT_START to set
	 */
	public void setDT_START(Date dT_START) {
		DT_START = dT_START;
	}

	/**
	 * @return the dT_END
	 */
	public Date getDT_END() {
		return DT_END;
	}

	/**
	 * @param dT_END the dT_END to set
	 */
	public void setDT_END(Date dT_END) {
		DT_END = dT_END;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the requestType
	 */
	public String getRequestType() {
		return requestType;
	}

	/**
	 * @param requestType the requestType to set
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	/**
	 * @return the statusType
	 */
	public String getStatusType() {
		return statusType;
	}

	/**
	 * @param statusType the statusType to set
	 */
	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	/**
	 * @return the sequence
	 */
	public String getSequence() {
		return sequence;
	}

	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
}
