package com.pmbox.pm.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.pmbox.pm.dao.DAO;
import com.pmbox.pm.dao.ProjectUserDAO;
import com.pmbox.pm.entity.Project;
import com.pmbox.pm.entity.ProjectUser;
import com.pmbox.pm.entity.TodoComment;
import com.pmbox.pm.entity.User;
import com.pmbox.pm.exception.AdException;


public class Authentication {
	public static boolean islogin(HttpSession session){
		boolean status = false;
		if(session.getAttribute("user") != null)
			status = true;
		return status;
	}
	public static boolean isAdmin(User user){
		boolean status = false;
		if(user.getRole().equals("admin"))
			status = true;
		return status;
	}
	public static boolean isUser(User user){
		boolean status = false;
		if(user.getRole().equals("user"))
			status = true;
		return status;
	}	
	
//	public static boolean isProjectAdmin(User user,int projectID){
//		boolean status = false;
//		try
//		{
//		    ProjectUserDAO projectUDao = new ProjectUserDAO();	
//		    String puRole = projectUDao.getProjectRole(projectID, user.getUserID());
//		    if(puRole.equals("ProjectAdmin")) status = true;
//		    DAO.close();
//		}
//		catch (AdException e)
//		{
//		    System.out.println("Exception: " + e.getMessage());
//		}
//		return status;
//	}
//	
//	public static boolean isProjectMember(User user,int projectID){
//		boolean status = false;
//		try
//		{
//		    ProjectUserDAO projectUDao = new ProjectUserDAO();	
//		    String puRole = projectUDao.getProjectRole(projectID, user.getUserID());
//		    if(puRole.equals("ProjectMember")) status = true;
//		    DAO.close();
//		}
//		catch (AdException e)
//		{
//		    System.out.println("Exception: " + e.getMessage());
//		}
//		return status;
//	}
//	
//	public static boolean isProjectClient(User user,int projectID){
//		boolean status = false;
//		try
//		{
//		    ProjectUserDAO projectUDao = new ProjectUserDAO();	
//		    String puRole = projectUDao.getProjectRole(projectID, user.getUserID());
//		    if(puRole.equals("ProjectClient")) status = true;
//		    DAO.close();
//		}
//		catch (AdException e)
//		{
//		    System.out.println("Exception: " + e.getMessage());
//		}
//		return status;
//	}
	
	public static boolean isProjectAdmin(HttpSession session,int projectID){
		boolean status = false;
		User user = (User)(session.getAttribute("user"));
		List rawlist = (List)(session.getAttribute("currentPUs"));
		for(Object o: rawlist){
			ProjectUser temppuser = (ProjectUser)o;
			if(temppuser.getProjectID() == projectID
					&& temppuser.getUserID() == user.getUserID()
					&& temppuser.getRole().equals("ProjectAdmin"))
				status = true;
		}
		return status;
	}
	
	public static boolean isProjectMember(HttpSession session,int projectID){
		boolean status = false;
		User user = (User)(session.getAttribute("user"));
		List rawlist = (List)(session.getAttribute("currentPUs"));
		for(Object o: rawlist){
			ProjectUser temppuser = (ProjectUser)o;
			if(temppuser.getProjectID() == projectID
					&& temppuser.getUserID() == user.getUserID()
					&& temppuser.getRole().equals("ProjectMember"))
				status = true;
		}
		return status;
	}
	
	public static boolean isProjectClient(HttpSession session,int projectID){
		boolean status = false;
		User user = (User)(session.getAttribute("user"));
		List rawlist = (List)(session.getAttribute("currentPUs"));
		for(Object o: rawlist){
			ProjectUser temppuser = (ProjectUser)o;
			if(temppuser.getProjectID() == projectID
					&& temppuser.getUserID() == user.getUserID()
					&& temppuser.getRole().equals("ProjectClient"))
				status = true;
		}
		return status;
	}
	
	public static boolean isCommenter(HttpSession session, TodoComment comment){
		boolean status = false;
		User user = (User)(session.getAttribute("user"));
		if(user.getUserID() == comment.getCommenterID())
			status = true;
		return status;
	}
}
