package com.pmbox.pm.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.pmbox.pm.entity.Project;
import com.pmbox.pm.entity.ProjectUser;
import com.pmbox.pm.exception.AdException;



public class ProjectUserDAO extends DAO {
	public ProjectUserDAO(){
		
	}	
	// get a projectUser with projectID and userID
	public String getProjectRole(Integer projectID, Integer userID) throws AdException{
		try{
			ProjectUser pu;
			begin();
			Query q = getSession().createQuery("from ProjectUser where projectID = :projectID and userID = :userID");
			q.setString("projectID", String.valueOf(projectID));
			q.setString("userID", String.valueOf(userID));
			pu = (ProjectUser)q.uniqueResult();
			commit();
			
			return pu.getRole();
		}catch(HibernateException e){
			rollback();
			throw new AdException("Could not get ProjectUser projectID:" + projectID + "; userID:"+ userID, e);
		}
	}
	
	//get a user's all involved projects via userID
	public List<ProjectUser> getProjectsByUserID(int userID) throws AdException{
		try{
			begin();
			Query q = getSession().createQuery("from ProjectUser where userID = :userID");
			q.setString("userID", String.valueOf(userID));
			List rawlist = q.list();
			commit();
			List<ProjectUser> resultlist = new ArrayList<ProjectUser>();
			for(Object o: rawlist){
				resultlist.add((ProjectUser)o);
			}
			return resultlist;
		}catch(HibernateException e){
			rollback();
			throw new AdException("Could not find projects for user:" + userID, e);
		}
	}
	
	//get all user involved in a project via projectID
		public List<ProjectUser> getUsersByProjectID(int projectID) throws AdException{
			try{
				begin();
				Query q = getSession().createQuery("from ProjectUser where projectID = :projectID");
				q.setString("projectID", String.valueOf(projectID));
				List rawlist = q.list();
				commit();
				List<ProjectUser> resultlist = new ArrayList<ProjectUser>();
				for(Object o: rawlist){
					resultlist.add((ProjectUser)o);
				}
				return resultlist;
			}catch(HibernateException e){
				rollback();
				throw new AdException("Could not find users for project:" + projectID, e);
			}
		}
	
	// create a ProjectUser with userID and projectID
	public ProjectUser create(Integer projectID, Integer userID, String role) throws AdException{
		try{
			ProjectUser puser = new ProjectUser(projectID, userID, role);
			
			begin();
			getSession().save(puser);
			commit();
			
			return puser;
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not create ProjectUser with pID:" + projectID + "; uID:" + userID, e);
		}
	}
	
	public void delete(ProjectUser puser) throws AdException{
		try{
			begin();
			getSession().delete(puser);
			commit();

		}catch(HibernateException e){
			rollback();
			throw new AdException("Could not delete ProjectUser: projectID{" + puser.getProjectID()+ "}; userID{" + puser.getUserID() + "}", e);
		}
	}
}
