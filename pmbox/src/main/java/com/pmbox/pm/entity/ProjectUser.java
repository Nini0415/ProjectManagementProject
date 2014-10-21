package com.pmbox.pm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="projectuser_table")
public class ProjectUser {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="puID", unique=true, nullable=false)
	private Integer puID;
	
	@Column(name="projectID", nullable=false)
	private Integer projectID;
	@Column(name="userID", nullable=false)
	private Integer userID;

	@Column(name="inProjectRole")
	private String role;
	public ProjectUser() {
	}
	public ProjectUser(Integer projectID, Integer userID, String role){
		this();
		this.projectID = projectID;
		this.userID = userID;
		this.role = role;
	}
	public Integer getPuID() {
		return puID;
	}
	public void setPuID(Integer puID) {
		this.puID = puID;
	}
	public Integer getProjectID() {
		return projectID;
	}
	public void setProjectID(Integer projectID) {
		this.projectID = projectID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
