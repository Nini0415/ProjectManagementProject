package com.pmbox.pm.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.pmbox.pm.entity.Task;
import com.pmbox.pm.exception.AdException;



public class TaskDAO extends DAO {
	public TaskDAO(){
		
	}
	
	// get all tasks with projectID
	public List<Task> getTaskByProjectID(int projectID) throws AdException{
		try{
			begin();
			Query q = getSession().createQuery("from Task where projectID = :projectID");
			q.setString("projectID", String.valueOf(projectID));
			//project = (User)q.uniqueResult();
			
			List rawlist = q.list();
			commit();
			List<Task> resultlist = new ArrayList<Task>();
			for(Object o: rawlist){
				resultlist.add((Task)o);
			}
			return resultlist;
		}catch(HibernateException e){
			rollback();
			throw new AdException("Could not get user" + projectID, e);
		}
	}
	// get task with taskID
	public Task get(int taskID) throws AdException{
		try{
			begin();
			Query q = getSession().createQuery("from Task where taskID = :taskID");
			q.setString("taskID", String.valueOf(taskID));
			Task task = (Task)q.uniqueResult();
			commit();
			return task;
		}catch(HibernateException e){
			rollback();
			throw new AdException("Could not get task" + taskID, e);
		}
	}
	
	// create a project with name and createDate, 
	// while status is default set as open
	public Task create(Integer projectID, String name, String createDate) throws AdException{
		try{
			Task task = new Task(projectID,name,createDate);			
			begin();
			getSession().save(task);
			commit();			
			return task;
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not create Task with " + name, e);
		}
	}
	
	public void delete(Task task) throws AdException{
		try{
			begin();
			getSession().delete(task);
			commit();

		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not delete project " + "{" + task.getTaskID() + "}", e);
		}
	}
}
