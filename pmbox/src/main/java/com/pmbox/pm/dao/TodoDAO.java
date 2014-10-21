package com.pmbox.pm.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.pmbox.pm.entity.NewUserRequest;
import com.pmbox.pm.entity.Task;
import com.pmbox.pm.entity.Todo;
import com.pmbox.pm.exception.AdException;



public class TodoDAO extends DAO {
	public TodoDAO(){
		
	}
	
	// get all todos with taskID
	public List<Todo> getByTaskID(int taskID) throws AdException{
		try{
			begin();
			Query q = getSession().createQuery("from Todo where taskID = :taskID");
			q.setString("taskID", String.valueOf(taskID));
			
			List rawlist = q.list();
			commit();
			List<Todo> resultlist = new ArrayList<Todo>();
			for(Object o: rawlist){
				resultlist.add((Todo)o);
			}
			return resultlist;
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not get todos " + taskID, e);
		}
	}
	
	public List<Todo> getByAssigneeID(int assigneeID) throws AdException{
		try{
			begin();
			Query q = getSession().createQuery("from Todo where assigneeID = :assigneeID");
			q.setString("assigneeID", String.valueOf(assigneeID));
			
			List rawlist = q.list();
			commit();
			List<Todo> resultlist = new ArrayList<Todo>();
			for(Object o: rawlist){
				resultlist.add((Todo)o);
			}
			return resultlist;
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not get todos for assigneeID: " + assigneeID, e);
		}
	}
	
	// get todo with todoID
		public Todo get(int todoID) throws AdException{
			try{
				begin();
				Query q = getSession().createQuery("from Todo where todoID = :todoID");
				q.setString("todoID", String.valueOf(todoID));
				Todo todo = (Todo)q.uniqueResult();
				commit();
				return todo;
			}catch(HibernateException e){
				rollback();
				e.printStackTrace();
				throw new AdException("Could not get todo: " + todoID, e);
			}
		}
	
	// create a project with name and createDate, 
	// while status is default set as open
	public Todo create(Integer taskID, Integer createrID, String createDate,
			String description) throws AdException{
		try{
			Todo todo = new Todo(taskID, createrID, createDate, description);			
			begin();
			getSession().save(todo);
			commit();			
			return todo;
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not create todo with " + description, e);
		}
	}
	
	public void save(Todo todo) throws AdException{
		try{
			begin();
			getSession().update(todo);
			commit();
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not save todo {" + todo.getTodoID() + "}", e);
		}
	}
	
	public void delete(Todo todo) throws AdException{
		try{
			begin();
			getSession().delete(todo);
			commit();

		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not delete project " + "{" + todo.getTodoID() + "}", e);
		}
	}
}
