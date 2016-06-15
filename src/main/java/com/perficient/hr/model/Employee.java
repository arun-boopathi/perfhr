package com.perficient.hr.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "employee")
@SuppressWarnings("serial")
public class Employee extends AbstractModel {

	@Id
	@GeneratedValue
	@Column(name = "pk", length = 11 )
	private Long pk;
	
	@Column(name = "employee_id")
	private String employeeId;
	
	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;

	@Column(name = "middlename")
	private String middleName;
	
	@Column(name = "contact_no")
	private String contactNo;
	
	@Column(name = "email")
	private String email;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "designation")
	private Designations designations;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "pincode")
	private String pincode;
	
	@Column(name = "nationality")
	private String nationality;
	
	@Column(name = "supervisor")
	private Long supervisor;
	
	@Column(name = "dob")
	private Date dob;
	
	@Column(name = "blood_group")
	private String bloodGroup;
	
	@Column(name = "pan_card_no")
	private String panCard;
	
	@Column(name = "joindate")
	private Date joinDate;
	
	@Column(name = "skills")
	private String skills;
	
	@Column(name = "last_working_Date")
	private Date lastWorkDate;
		
	@Column(name = "billable")
	private int billable;
	
	@Column(name = "gender")
	private String gender;
	
	@Transient
	private String superviserFirstName;
	
	@Transient
	private String superviserLastName;
	
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the contactNo
	 */
	public String getContactNo() {
		return contactNo;
	}

	/**
	 * @param contactNo the contactNo to set
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}

	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * @return the designations
	 */
	public Designations getDesignations() {
		return designations;
	}

	/**
	 * @param designations the designations to set
	 */
	public void setDesignations(Designations designations) {
		this.designations = designations;
	}

	/**
	 * @return the supervisor
	 */
	public Long getSupervisor() {
		return supervisor;
	}

	/**
	 * @param supervisor the supervisor to set
	 */
	public void setSupervisor(Long supervisor) {
		this.supervisor = supervisor;
	}

	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * @return the bloodGroup
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}

	/**
	 * @param bloodGroup the bloodGroup to set
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	/**
	 * @return the panCard
	 */
	public String getPanCard() {
		return panCard;
	}

	/**
	 * @param panCard the panCard to set
	 */
	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	/**
	 * @return the joinDate
	 */
	public Date getJoinDate() {
		return joinDate;
	}

	/**
	 * @param joinDate the joinDate to set
	 */
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	/**
	 * @return the skills
	 */
	public String getSkills() {
		return skills;
	}

	/**
	 * @param skills the skills to set
	 */
	public void setSkills(String skills) {
		this.skills = skills;
	}

	/**
	 * @return the lastWorkDate
	 */
	public Date getLastWorkDate() {
		return lastWorkDate;
	}

	/**
	 * @param lastWorkDate the lastWorkDate to set
	 */
	public void setLastWorkDate(Date lastWorkDate) {
		this.lastWorkDate = lastWorkDate;
	}

	/**
	 * @return the billable
	 */
	public int getBillable() {
		return billable;
	}

	/**
	 * @param billable the billable to set
	 */
	public void setBillable(int billable) {
		this.billable = billable;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the superviserFirstName
	 */
	public String getSuperviserFirstName() {
		return superviserFirstName;
	}

	/**
	 * @param superviserFirstName the superviserFirstName to set
	 */
	public void setSuperviserFirstName(String superviserFirstName) {
		this.superviserFirstName = superviserFirstName;
	}

	/**
	 * @return the superviserLastName
	 */
	public String getSuperviserLastName() {
		return superviserLastName;
	}

	/**
	 * @param superviserLastName the superviserLastName to set
	 */
	public void setSuperviserLastName(String superviserLastName) {
		this.superviserLastName = superviserLastName;
	}
	
	@Override 
	public boolean equals(Object obj) {
	  if (obj == this) {
		  return true;
	  }
	  if (obj == null || obj.getClass() != this.getClass()) {
		  return false;
	  }
	  Employee employee = (Employee) obj;
	  return pk == employee.pk 
			  && (address == employee.address || (address != null && address.equals(employee.getAddress())))
			  && (bloodGroup == employee.bloodGroup || (bloodGroup != null && bloodGroup.equals(employee.getBloodGroup())))
			  && (city == employee.city || (city != null && city.equals(employee.getCity())))
			  && (contactNo == employee.contactNo || (contactNo != null && contactNo.equals(employee.getContactNo())))
			  && (email == employee.email || (email != null && email.equals(employee.getEmail())))
			  && (employeeId == employee.employeeId || (employeeId != null && employeeId.equals(employee.getEmployeeId())))
			  && (firstName == employee.firstName || (firstName != null && firstName.equals(employee.getFirstName())))
			  && (gender == employee.gender || (gender != null && gender.equals(employee.getGender())))
			  && (lastName == employee.lastName || (lastName != null && lastName.equals(employee.getLastName())))
			  && (lastWorkDate == employee.lastWorkDate || (lastWorkDate != null && lastWorkDate.equals(employee.getLastWorkDate())))
			  && (middleName == employee.middleName || (middleName != null && middleName.equals(employee.getMiddleName())))
			  && (nationality == employee.nationality || (nationality != null && nationality.equals(employee.getNationality())))
			  && (panCard == employee.panCard || (panCard != null && panCard.equals(employee.getPanCard())))
			  && (pincode == employee.pincode || (pincode != null && pincode.equals(employee.getPincode())))
			  && (skills == employee.skills || (skills != null && skills.equals(employee.getSkills())))
			  && (supervisor == employee.supervisor || (null != supervisor && supervisor.equals(employee.getSupervisor())))
			  && (superviserFirstName == employee.superviserFirstName || (superviserFirstName != null && superviserFirstName.equals(employee.getSuperviserFirstName())))
			  && (superviserLastName == employee.superviserLastName || (superviserLastName != null && superviserLastName.equals(employee.getSuperviserLastName())));
	 }
	
}
