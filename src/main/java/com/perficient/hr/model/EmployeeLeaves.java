package com.perficient.hr.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "employee_leaves")
public class EmployeeLeaves extends AbstractModel{

	@Id
	@GeneratedValue
	@Column(name = "pk", length = 11 )
	private Long pk;
	
	@Column(name = "employee_pk")
	private Long employeeId;
	
	@Column(name = "request_type")
	private String requestType;
	
	@Column(name = "request_title")
	private String title;
	
	@Column(name = "comments")
	private String comments;
	
	@Column(name = "dt_from")
	private Date startsAt;
	
	@Column(name = "dt_from_half")
	private String dtFromHalf;
	
	@Column(name = "dt_End")
	private Date endsAt;
	
	@Column(name = "dt_end_half")
	private String dtEndHalf;
	
	@Column(name = "hours")
	private int hours;
	
	@Transient
	private String type;

	/**
	 * @return the pk
	 */
	public Long getPk() {
		return pk;
	}

	/**
	 * @param pk the pk to set
	 */
	public void setPk(Long pk) {
		this.pk = pk;
	}

	/**
	 * @return the employeeId
	 */
	public Long getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the startsAt
	 */
	public Date getStartsAt() {
		return startsAt;
	}

	/**
	 * @param startsAt the startsAt to set
	 */
	public void setStartsAt(Date startsAt) {
		this.startsAt = startsAt;
	}

	/**
	 * @return the dtFromHalf
	 */
	public String getDtFromHalf() {
		return dtFromHalf;
	}

	/**
	 * @param dtFromHalf the dtFromHalf to set
	 */
	public void setDtFromHalf(String dtFromHalf) {
		this.dtFromHalf = dtFromHalf;
	}

	/**
	 * @return the endsAt
	 */
	public Date getEndsAt() {
		return endsAt;
	}

	/**
	 * @param endsAt the endsAt to set
	 */
	public void setEndsAt(Date endsAt) {
		this.endsAt = endsAt;
	}

	/**
	 * @return the dtEndHalf
	 */
	public String getDtEndHalf() {
		return dtEndHalf;
	}

	/**
	 * @param dtEndHalf the dtEndHalf to set
	 */
	public void setDtEndHalf(String dtEndHalf) {
		this.dtEndHalf = dtEndHalf;
	}

	/**
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * @param hours the hours to set
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
}
