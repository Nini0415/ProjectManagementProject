package com.pmbox.pm.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="user_table",
		uniqueConstraints=@UniqueConstraint(columnNames={"userName"}))
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userID", unique=true, nullable=false)
	private Integer userID;
	
	@NotBlank(message = "Input cannot only contain spaces!")
	@Size(min = 1, message = "User name must be longer than 1 character!")
	@Column(name="userName", unique=true, nullable=false, length=45)
//	@Column(name="userName", unique=true, nullable=false, length=45)
	private String userName;
	
	@NotBlank(message = "Input cannot only contain spaces!")
	@Size(min = 1, message = "Password must be longer than 1 character!")
	@Column(name="password", length=45)
//	@Column(name="password", nullable=false, length=45)
	private String password;
	
	@Column(name="role", length=45)
//	@Column(name="role", nullable=false, length=45)
	private String role;
	public User() {
	}
	
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name = "projectuser_table", catalog = "pmbox", joinColumns = { 
//			@JoinColumn(name = "userID", nullable = false, updatable = false) }, 
//			inverseJoinColumns = { @JoinColumn(name = "projectID", 
//					nullable = false, updatable = false) })
//	private Set<Project> userProjects = new HashSet<Project>(0);
	public User(String userName, String password, String role) {
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
//	public Set<Project> getUserProjects() {
//		return userProjects;
//	}
//	public void setUserProjects(Set<Project> userProjects) {
//		this.userProjects = userProjects;
//	}
	
	
}
