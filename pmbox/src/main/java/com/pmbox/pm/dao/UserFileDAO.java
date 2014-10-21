package com.pmbox.pm.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.pmbox.pm.entity.UserFile;
import com.pmbox.pm.entity.UserFile;
import com.pmbox.pm.exception.AdException;



public class UserFileDAO extends DAO {
	public UserFileDAO(){
		
	}
	
	// get all comments with todoID
	public List<UserFile> getByProjectID(int projectID) throws AdException{
		try{
			begin();
			Query q = getSession().createQuery("from UserFile where projectID = :projectID");
			q.setString("projectID", String.valueOf(projectID));			
			List rawlist = q.list();
			commit();
			List<UserFile> resultlist = new ArrayList<UserFile>();
			for(Object o: rawlist){
				resultlist.add((UserFile)o);
			}
			return resultlist;
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not get UserFile for projectID:" + projectID, e);
		}
	}
	
	//get UserFile with UserFileID
	public UserFile get(int fileID) throws AdException{
		try{
			begin();
			Query q = getSession().createQuery("from UserFile where fileID = :fileID");
			q.setString("fileID", String.valueOf(fileID));
			UserFile file = (UserFile)q.uniqueResult();
			commit();
			return file;
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not get UserFile for UserFileID:" + fileID, e);
		}
	}

	public UserFile create(UserFile file) throws AdException{
		try{		
			begin();
			getSession().save(file);
			commit();			
			return file;
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not create UserFile with name" + file.getName(), e);
		}
	}
	
	public void delete(UserFile file) throws AdException{
		try{
			begin();
			getSession().delete(file);
			commit();

		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not delete UserFile " + "{" + file.getFileID() + "}", e);
		}
	}
}
