package com.perficient.hr.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee_projects")
@SuppressWarnings("serial")
public class ProjectMembers implements Serializable{

	@Id
	@GeneratedValue
	@Column(name = "pk", length = 11 )
	private Long pk;
	
	@Column(name = "employee_pk")
	private String employeeId;
	
	@Column(name = "project_pk")
	private String projectId;
	
	@Column(name = "dt_started")
	private Date dtStarted;
	
	@Column(name = "dt_ended")
	private Date dtEnded;
	
	@Column(name = "dt_created")
	private Date dtCreated;
	
	@Column(name = "dt_modified")
	private Date dtModified;

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
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the dtStarted
	 */
	public Date getDtStarted() {
		return dtStarted;
	}

	/**
	 * @param dtStarted the dtStarted to set
	 */
	public void setDtStarted(Date dtStarted) {
		this.dtStarted = dtStarted;
	}

	/**
	 * @return the dtEnded
	 */
	public Date getDtEnded() {
		return dtEnded;
	}

	/**
	 * @param dtEnded the dtEnded to set
	 */
	public void setDtEnded(Date dtEnded) {
		this.dtEnded = dtEnded;
	}

	/**
	 * @return the dtCreated
	 */
	public Date getDtCreated() {
		System.out.println("getDtCreated "+dtCreated);
		return dtCreated;
	}

	/**
	 * @param dtCreated the dtCreated to set
	 */
	public void setDtCreated(Date dtCreated) {
		this.dtCreated = dtCreated;
	}

	/**
	 * @return the dtModified
	 */
	public Date getDtModified() {
		return dtModified;
	}

	/**
	 * @param dtModified the dtModified to set
	 */
	public void setDtModified(Date dtModified) {
		this.dtModified = dtModified;
	}
	
}
