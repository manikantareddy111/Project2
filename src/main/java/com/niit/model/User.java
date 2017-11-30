package com.niit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User 
{
	@Id
	@GeneratedValue
	private int UserdId;
	private String FirstName;
	private String LastName;
	private String Password;
	private String EmailId;
	private String role;
	private String status;
	private String isOnine;
	public int getUserdId() {
		return UserdId;
	}
	public void setUserdId(int userdId) {
		UserdId = userdId;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getEmailId() {
		return EmailId;
	}
	public void setEmailId(String emailId) {
		EmailId = emailId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsOnine() {
		return isOnine;
	}
	public void setIsOnine(String isOnine) {
		this.isOnine = isOnine;
	}
	
}
