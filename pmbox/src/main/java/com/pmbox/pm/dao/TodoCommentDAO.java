package com.pmbox.pm.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.pmbox.pm.entity.Todo;
import com.pmbox.pm.entity.TodoComment;
import com.pmbox.pm.exception.AdException;



public class TodoCommentDAO extends DAO {
	public TodoCommentDAO(){
		
	}
	
	// get all comments with todoID
	public List<TodoComment> getByTodoID(int todoID) throws AdException{
		try{
			begin();
			Query q = getSession().createQuery("from TodoComment where todoID = :todoID");
			q.setString("todoID", String.valueOf(todoID));
			
			List rawlist = q.list();
			commit();
			List<TodoComment> resultlist = new ArrayList<TodoComment>();
			for(Object o: rawlist){
				resultlist.add((TodoComment)o);
			}
			return resultlist;
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not get comments for todoID:" + todoID, e);
		}
	}
	// get comment with commentID
	public TodoComment get(int commentID) throws AdException{
		try{
			begin();
			Query q = getSession().createQuery("from TodoComment where commentID = :commentID");
			q.setString("commentID", String.valueOf(commentID));
			TodoComment comment = (TodoComment)q.uniqueResult();
			commit();
			return comment;
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not get commentID: " + commentID, e);
		}
	}
			
	// while status is default set as open
	public TodoComment create(Integer todoID, Integer commenterID, 
			String commentDate, String content) throws AdException{
		try{
			TodoComment comment = new TodoComment(todoID, commenterID, commentDate, content);			
			begin();
			getSession().save(comment);
			commit();			
			return comment;
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not create comment with " + content, e);
		}
	}
	
	public void delete(TodoComment comment) throws AdException{
		try{
			begin();
			getSession().delete(comment);
			commit();

		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not delete project " + "{" + comment.getCommenterID() + "}", e);
		}
	}
}
