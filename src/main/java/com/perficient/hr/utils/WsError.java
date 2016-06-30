package com.perficient.hr.utils;

public class WsError {
	
	private String errorMessage;
	private Object exceptionObj;
	private Integer errorCode;

	public WsError() {
	}
	
	public WsError(String errorMessage, Object exceptionObj, Integer errorCode) {
		this.errorMessage = errorMessage;
		this.exceptionObj = exceptionObj;
		this.errorCode = errorCode;
	}
	
	public WsError(String errorMessage, Object exceptionObj) {
		this.errorMessage = errorMessage;
		this.exceptionObj = exceptionObj;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Object getExceptionObj() {
		return exceptionObj;
	}
	public void setExceptionObj(Object exceptionObj) {
		this.exceptionObj = exceptionObj;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
}
