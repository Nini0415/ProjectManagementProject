package com.pmbox.pm.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.pmbox.pm.entity.User;
import com.pmbox.pm.exception.AdException;



public class UserDAO extends DAO {
	public UserDAO(){
		System.out.println("User DAO");
	}
	
	// get a user with username
	public User get(String username) throws AdException{
		try{
			User user = null;
			begin();
			Query q = getSession().createQuery("from User where userName = :username");
			q.setString("username", username);
			user = (User)q.uniqueResult();
			commit();
			
			return user;
		}catch(HibernateException e){
			rollback();
			throw new AdException("Could not get user" + username, e);
		}
	}
	
	// get a user with userID
		public User get(int userID) throws AdException{
			try{
				User user = null;
				begin();
				Query q = getSession().createQuery("from User where userID = :userID");
				q.setString("userID", String.valueOf(userID));
				user = (User)q.uniqueResult();
				commit();
				
				return user;
			}catch(HibernateException e){
				rollback();
				throw new AdException("Could not get userID: " + userID, e);
			}
		}
		
	// create a user with username and password
	public User create(String username, String password, String role) throws AdException{
		try{
			User user = new User(username, password, role);
			
			begin();
			getSession().save(user);
			commit();
			
			return user;
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not create user with " + username, e);
		}
	}
	
	public User create(User user) throws AdException{
		try{
			begin();
			getSession().save(user);
			commit();
			
			return user;
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not create user with " + user.getUserName(), e);
		}
	}
	
	public void save(User user) throws AdException{
		try{
			begin();
			getSession().update(user);
			commit();
		}catch(HibernateException e){
			rollback();
			throw new AdException("Could not save"+ "user ID {" + user.getUserID() + "}", e);
		}
	}
	
	public void delete(User user) throws AdException{
		try{
			begin();
			getSession().delete(user);
			commit();

		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not delete user " + "{" + user.getUserName() + "}", e);
		}
	}
}
