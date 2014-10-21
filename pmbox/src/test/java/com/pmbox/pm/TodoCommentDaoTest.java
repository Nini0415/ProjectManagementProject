package com.pmbox.pm;

import java.util.List;

import com.pmbox.pm.dao.DAO;
import com.pmbox.pm.dao.TodoCommentDAO;
import com.pmbox.pm.entity.TodoComment;
import com.pmbox.pm.exception.AdException;


public class TodoCommentDaoTest {
	
	public static void main(String[] args){
		TodoComment comment1 = new TodoComment(1,2,"04/15/2014","first comment for todo1");
		TodoComment comment2 = new TodoComment(1,2,"04/15/2014","second comment for todo1");
		try
	    {
	        TodoCommentDAO TodoCommentDAO = new TodoCommentDAO();
	        
	        TodoCommentDAO.create(comment1.getTodoID(),comment1.getCommenterID(), comment1.getCommentDate(), comment1.getContent());
	        TodoCommentDAO.create(comment2.getTodoID(),comment2.getCommenterID(), comment2.getCommentDate(), comment2.getContent());
	        List<TodoComment> testresult = TodoCommentDAO.getByTodoID(1);
	        for(TodoComment t: testresult){
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
