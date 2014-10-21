package com.pmbox.pm.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.pmbox.pm.entity.Project;
import com.pmbox.pm.entity.Task;
import com.pmbox.pm.exception.AdException;



public class ProjectDAO extends DAO {
	public ProjectDAO(){
		
	}
	
	// get a project with projectID
	public Project get(int projectID) throws AdException{
		try{
			Project project = null;
			begin();
			Query q = getSession().createQuery("from Project where projectID = :projectID");
			q.setString("projectID", String.valueOf(projectID));
			project = (Project)q.uniqueResult();
			commit();			
			return project;
		}catch(HibernateException e){
			rollback();
			throw new AdException("Could not get project:" + projectID, e);
		}
	}
	
	
	
	// create a project with name and createDate, 
	// while status is default set as open
	public Project create(String name, String createDate) throws AdException{
		try{
			Project project = new Project(name,createDate);
			
			begin();
			getSession().save(project);
			commit();
			
			return project;
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not create project with " + name, e);
		}
	}
	
	public void delete(Project project) throws AdException{
		try{
			begin();
			getSession().delete(project);
			commit();

		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not delete project " + "{" + project.getProjectID() + "}", e);
		}
	}
}
