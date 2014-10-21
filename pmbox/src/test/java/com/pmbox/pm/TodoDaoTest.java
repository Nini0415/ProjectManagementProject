package com.pmbox.pm;

import java.util.List;

import com.pmbox.pm.dao.DAO;
import com.pmbox.pm.dao.TodoDAO;
import com.pmbox.pm.entity.Todo;
import com.pmbox.pm.exception.AdException;


public class TodoDaoTest {
	
	public static void main(String[] args){
//		Todo todo1 = new Todo(1,2,"04/15/2014","pro1 task1 todo1");
//		Todo todo2 = new Todo(1,2,"04/15/2014","pro1 task1 todo2");
		int todoID = 7;
		try
	    {
	        TodoDAO todoDAO = new TodoDAO();
	        
//	        todoDAO.create(todo1.getTaskID(),todo1.getCreaterID(),todo1.getCreateDate(),todo1.getDescription());
//	        todoDAO.create(todo2.getTaskID(),todo2.getCreaterID(),todo2.getCreateDate(),todo2.getDescription());
	        List<Todo> testresult = todoDAO.getByTaskID(1);
	        for(Todo t: testresult){
	        	System.out.println(t);
	        }
	        Todo todo = todoDAO.get(todoID);
	        todo.setAssigneeID(4);
	        todoDAO.save(todo);
	        
	        DAO.close();
	    }
	    catch (AdException e)
	    {
	        System.out.println("Exception: " + e.getMessage());
	    }
	}
}
