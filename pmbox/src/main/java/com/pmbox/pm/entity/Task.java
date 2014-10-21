package com.pmbox.pm.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="task_table")
public class Task {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="taskID", unique=true, nullable=false)
	private Integer taskID;
	
	@Column(name="projectID")
	private Integer projectID;
	@Column(name="name")
	private String name;
	@Column(name="startDate")
	private String startDate;
	@Column(name="dueDate")
	private String dueDate;
	@Column(name="status")
	private String status;
	@Column(name="createDate")
	private String createDate;
	
	public Task() {
	}
	public Task(Integer projectID, String name, String createDate){
		this.projectID = projectID;
		this.name = name;
		this.createDate = createDate;
		this.status = "Open";
	}
	public int getTaskID() {
		return taskID;
	}
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	public int getProjectID() {
		return projectID;
	}
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String toString(){
		return name;
	}
	
}
