package com.pmbox.pm;

import java.util.List;

import com.pmbox.pm.dao.DAO;
import com.pmbox.pm.dao.ProjectDAO;
import com.pmbox.pm.dao.ProjectUserDAO;
import com.pmbox.pm.entity.Project;
import com.pmbox.pm.entity.ProjectUser;
import com.pmbox.pm.exception.AdException;


public class ProjectUserDaoTest {
	
	public static void main(String[] args){
		//create association test
//		ProjectUser puser = new ProjectUser(4, 2, "ProjectAdmin");
//		try
//	    {
//			ProjectUserDAO projectuserDao = new ProjectUserDAO();
//			projectuserDao.create(puser.getProjectID(), puser.getUserID(), puser.getRole());
//	        DAO.close();
//	    }
//	    catch (AdException e)
//	    {
//	        System.out.println("Exception: " + e.getMessage());
//	    }
		
		// get in project role test
//		try
//	    {
//			ProjectUserDAO projectuserDao = new ProjectUserDAO();
//			String role = projectuserDao.getProjectRole(1, 2);
//			System.out.println(role);
//	        DAO.close();
//	    }
//	    catch (AdException e)
//	    {
//	        System.out.println("Exception: " + e.getMessage());
//	    }
		
		//delete association test
		try
		{
		    ProjectUserDAO projectUDao = new ProjectUserDAO();	
		    List<ProjectUser> uprojects = projectUDao.getProjectsByUserID(2);
		    for(ProjectUser p: uprojects){
		    	System.out.println("userID 2's project:" + p.getProjectID());
		    }
		    DAO.close();
		}
		catch (AdException e)
		{
		    System.out.println("Exception: " + e.getMessage());
		}
		//get by userID
		try
		{
		    ProjectUserDAO projectUDao = new ProjectUserDAO();	
		    List<ProjectUser> uprojects = projectUDao.getProjectsByUserID(2);
		    for(ProjectUser p: uprojects){
		    	System.out.println("userID 2's project:" + p.getProjectID());
		    }
		    DAO.close();
		}
		catch (AdException e)
		{
		    System.out.println("Exception: " + e.getMessage());
		}
	}
}
