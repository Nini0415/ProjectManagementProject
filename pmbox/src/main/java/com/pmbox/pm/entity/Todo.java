package com.pmbox.pm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="todo_table")
public class Todo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="todoID", unique=true, nullable=false)
	private Integer todoID;
	
	@Column(name="taskID")
	private Integer taskID;
	
	@Column(name="createrID")
	private Integer createrID;
	
	@Column(name="assigneeID")
	private Integer assigneeID;
	
	@Column(name="description")
	private String description;
	
	@Column(name="createDate")
	private String createDate;
	
	@Column(name="startDate")
	private String startDate;
	
	@Column(name="dueDate")
	private String dueDate;
	
	@Column(name="status")
	private String status;
	public Todo() {
	}
	public Todo(Integer taskID, Integer createrID, String createDate,
			String description) {
		this.taskID = taskID;
		this.createrID = createrID;
		this.createDate = createDate;
		this.description = description;
		this.status = "Open";
		this.assigneeID = 0; // Initialize assigneeID
	}
	public int getTodoID() {
		return todoID;
	}
	public void setTodoID(int todoID) {
		this.todoID = todoID;
	}
	public int getTaskID() {
		return taskID;
	}
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	public int getCreaterID() {
		return createrID;
	}
	public void setCreaterID(int createrID) {
		this.createrID = createrID;
	}
	public int getAssigneeID() {
		return assigneeID;
	}
	public void setAssigneeID(int assigneeID) {
		this.assigneeID = assigneeID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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
	public String toString(){
		return "Todo"+todoID +" "+description;
	}
	public String getStartYear() {
		if(startDate == null) return "1900";
		return startDate.split(" ")[0].split("/")[2];
	}
	//for display compatibility need, the return value is 1 less than actual month!
	public String getStartMonth() {
		if(startDate == null) return "1";
		String tempMonth = startDate.split(" ")[0].split("/")[0];
		return  Integer.parseInt(tempMonth)-1 +"";
	}
	public String getStartDay() {
		if(startDate == null) return "1";
		return  startDate.split(" ")[0].split("/")[1];
	}
	public String getDueYear() {
		if(dueDate == null) return "1900";
		return dueDate.split(" ")[0].split("/")[2];
	}
	//for display compatibility need, the return value is 1 less than actual month!
	public String getDueMonth() {
		if(dueDate == null) return "1";
		String tempMonth = dueDate.split(" ")[0].split("/")[0];
		return  Integer.parseInt(tempMonth)-1 +"";
	}
	public String getDueDay() {
		if(dueDate == null) return "1";
		return  dueDate.split(" ")[0].split("/")[1];
	}
	
	// Test getDue Month/Day/Year
//	public static void main(String[] args){
//		Todo todo = new Todo();
//		System.out.println("DueM:"+todo.getStartYear());
//	}
}
