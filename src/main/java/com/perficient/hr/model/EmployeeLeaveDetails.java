package com.perficient.hr.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_leaves_details")
@SuppressWarnings("serial")
public class EmployeeLeaveDetails  extends AbstractModel implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "pk", length = 11 )
	private Long pk;
	
	@Column(name = "employee_leaves_pk")
	private Long employeeLeavesId;
	
	@Column(name = "date")
	private Date leaveDate;
	
	@Column(name = "hours")
	private int hours;

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
	 * @return the employeeLeavesId
	 */
	public Long getEmployeeLeavesId() {
		return employeeLeavesId;
	}

	/**
	 * @param employeeLeavesId the employeeLeavesId to set
	 */
	public void setEmployeeLeavesId(Long employeeLeavesId) {
		this.employeeLeavesId = employeeLeavesId;
	}

	/**
	 * @return the leaveDate
	 */
	public Date getLeaveDate() {
		return leaveDate;
	}

	/**
	 * @param leaveDate the leaveDate to set
	 */
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
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
	
}
