package com.pmbox.pm.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="project_table")
public class Project {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="projectID", unique=true, nullable=false)
	private Integer projectID;
	
	@Column(name="name")
	private String name;
	
	@Column(name="createDate")
	private String createDate;
	
	@Column(name="status")
	private String status;
	
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name = "projectuser_table", catalog = "pmbox", joinColumns = { 
//			@JoinColumn(name = "projectID", nullable = false, updatable = false) }, 
//			inverseJoinColumns = { @JoinColumn(name = "userID", 
//					nullable = false, updatable = false) })
//	private Set<User> projectUsers = new HashSet<User>(0);
	
	
	public Project() {
	}
	public Project(String name, String createDate){
		this.name = name;
		this.createDate = createDate;
		this.status = "Open";
	}
	public Integer getProjectID() {
		return projectID;
	}
	public void setProjectID(Integer projectID) {
		this.projectID = projectID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setArchive(){
		this.status = "Archived";
	}
	
	public void setOpen(){
		this.status = "Open";
	}
//	public Set<User> getProjectUsers() {
//		return projectUsers;
//	}
//	public void setProjectUsers(Set<User> projectUsers) {
//		this.projectUsers = projectUsers;
//	}	
}
