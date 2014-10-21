package com.pmbox.pm.service;

import java.util.ArrayList;
import java.util.List;

import com.pmbox.pm.entity.Task;
import com.pmbox.pm.entity.Todo;
import com.pmbox.pm.entity.User;

public class TaskTodo {
	private Task task = null;
	private List<Todo> todoList = null;
	private List<User> assigneeList = null;
	public TaskTodo(){
		todoList = new ArrayList<Todo>();
		assigneeList = new ArrayList<User>();
	}
	public List<User> getAssigneeList() {
		return assigneeList;
	}
	public void setAssigneeList(List<User> assigneeList) {
		this.assigneeList = assigneeList;
	}
	public TaskTodo(Task task){
		this();
		this.task = task;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public List<Todo> getTodoList() {
		return todoList;
	}
	public void setTodoList(List<Todo> todoList) {
		this.todoList = todoList;
	}
	public void addTodo(Todo todo){
		this.todoList.add(todo);
	}
	public void addAssignee(User assignee){
		this.assigneeList.add(assignee);
	}
}
