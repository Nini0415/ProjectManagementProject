package com.pmbox.pm;

import java.util.List;

import com.pmbox.pm.dao.DAO;
import com.pmbox.pm.dao.TaskDAO;
import com.pmbox.pm.entity.Task;
import com.pmbox.pm.exception.AdException;


public class TaskDaoTest {
	
	public static void main(String[] args){
		Task task1 = new Task(4, "project4 task1", "04/15/2014");
		Task task2 = new Task(4, "project4 task2", "04/15/2014");
		System.out.println("task1 id:"+task1.getName());
		try
	    {
	        TaskDAO taskDAO = new TaskDAO();
	        
	        taskDAO.create(task1.getProjectID(), task1.getName(), task1.getCreateDate());
	        taskDAO.create(task2.getProjectID(), task2.getName(), task2.getCreateDate());
	        List<Task> testresult = taskDAO.getTaskByProjectID(1);
	        for(Task t: testresult){
	        	System.out.println(t);
	        }
	        DAO.close();
	    }
	    catch (AdException e)
	    {
	        System.out.println("Exception: " + e.getMessage());
	    }
	}
}
