package com.pmbox.pm.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.pmbox.pm.entity.NewUserRequest;
import com.pmbox.pm.exception.AdException;



public class NewUserRequestDAO extends DAO {
	public NewUserRequestDAO(){
		
	}
	
	// get all requests with senderID
	public List<NewUserRequest> getBySenderID(int senderID) throws AdException{
		try{
			begin();
			Query q = getSession().createQuery("from NewUserRequest where senderID = :senderID");
			q.setString("senderID", String.valueOf(senderID));
			
			List rawlist = q.list();
			commit();
			List<NewUserRequest> resultlist = new ArrayList<NewUserRequest>();
			for(Object o: rawlist){
				resultlist.add((NewUserRequest)o);
			}
			return resultlist;
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not get requests sent from userID: " + senderID, e);
		}
	}
	
	// get all unhandled requests, where adminID = 0
		public List<NewUserRequest> getOpenRequests() throws AdException{
			try{
				begin();
				Query q = getSession().createQuery("from NewUserRequest where adminID = 0");
				List rawlist = q.list();
				commit();
				List<NewUserRequest> resultlist = new ArrayList<NewUserRequest>();
				for(Object o: rawlist){
					resultlist.add((NewUserRequest)o);
				}
				return resultlist;
			}catch(HibernateException e){
				rollback();
				e.printStackTrace();
				throw new AdException("Could not get open requests, error occur", e);
			}
		}
	
	// get todo with todoID
		public NewUserRequest get(int requestID) throws AdException{
			try{
				begin();
				Query q = getSession().createQuery("from NewUserRequest where requestID = :requestID");
				q.setString("requestID", String.valueOf(requestID));
				NewUserRequest request = (NewUserRequest)q.uniqueResult();
				commit();
				return request;
			}catch(HibernateException e){
				rollback();
				e.printStackTrace();
				throw new AdException("Could not get request: " + requestID, e);
			}
		}
	
	// create a project with name and createDate, 
	// while status is default set as open
	public NewUserRequest create(int senderID, String requestUserName, 
			String sendDate) throws AdException{
		try{
			NewUserRequest request = new NewUserRequest(senderID, requestUserName, sendDate);			
			begin();
			getSession().save(request);
			commit();			
			return request;
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not create request with required name: " + 
					requestUserName, e);
		}
	}
	
	public void save(NewUserRequest request) throws AdException{
		try{
			begin();
			getSession().update(request);
			commit();
		}catch(HibernateException e){
			rollback();
			throw new AdException("Could not save request {" + request.getRequestID() + "}", e);
		}
	}
	
//	public void delete(Todo todo) throws AdException{
//		try{
//			begin();
//			getSession().delete(todo);
//			commit();
//
//		}catch(HibernateException e){
//			rollback();
//			e.printStackTrace();
//			throw new AdException("Could not delete project " + "{" + todo.getTodoID() + "}", e);
//		}
//	}
}
