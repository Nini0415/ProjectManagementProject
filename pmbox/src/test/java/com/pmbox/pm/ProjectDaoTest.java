package com.pmbox.pm;

import java.util.List;

import com.pmbox.pm.dao.DAO;
import com.pmbox.pm.dao.ProjectDAO;
import com.pmbox.pm.entity.Project;
import com.pmbox.pm.exception.AdException;
import com.pmbox.pm.service.DateOperation;


public class ProjectDaoTest {
	
	public static void main(String[] args){
		//create test
		Project project = new Project("Project3",DateOperation.getCurrentTime());
		try
	    {
	        ProjectDAO projectDao = new ProjectDAO();	        
	        projectDao.create(project.getName(), project.getCreateDate());
	        DAO.close();
	    }
	    catch (AdException e)
	    {
	        System.out.println("Exception: " + e.getMessage());
	    }
		
		
	}
}
