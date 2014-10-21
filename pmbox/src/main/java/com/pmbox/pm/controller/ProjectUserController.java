package com.pmbox.pm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pmbox.pm.dao.DAO;
import com.pmbox.pm.dao.NewUserRequestDAO;
import com.pmbox.pm.dao.ProjectUserDAO;
import com.pmbox.pm.dao.UserDAO;
import com.pmbox.pm.entity.NewUserRequest;
import com.pmbox.pm.entity.ProjectUser;
import com.pmbox.pm.entity.User;
import com.pmbox.pm.exception.AdException;
import com.pmbox.pm.service.Authentication;
import com.pmbox.pm.service.DateOperation;

@Controller
public class ProjectUserController {
	@RequestMapping(value="ajaxSearchUser.htm", method=RequestMethod.GET)
	public String ajaxSearchUser(
			@RequestParam("userName") String userName, 
			@RequestParam("projectID") int projectID, 
			Model model, HttpSession session) {
		//Get user
		model.addAttribute("userName",userName);
		try
	    {
	        UserDAO userDao = new UserDAO();
	        User user = userDao.get(userName);
	        DAO.close();
	        model.addAttribute("ajaxUser", user);
	        model.addAttribute("ajaxProjectID", projectID);
	    }
	    catch (AdException e)
	    {
	        System.out.println("Exception: " + e.getMessage());
	    }
		return "ajaxresult";
	}
	
	@RequestMapping(value="ajaxRequest.htm", method=RequestMethod.GET)
	public String ajaxRequest(
			@RequestParam("userName") String userName, 
			Model model, HttpSession session) {
		if(!Authentication.islogin(session)) return "index";
		if(!Authentication.isUser((User)(session.getAttribute("user")))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");
			return "error";
		}
		User user = (User) session.getAttribute("user");
		int SUCCESS = 1;
		int FAIL = 0;
		try
	    {
			NewUserRequestDAO requestDAO = new NewUserRequestDAO();	 
			NewUserRequest request = requestDAO.create(user.getUserID(), userName, DateOperation.getCurrentTime());
	        DAO.close();
	        model.addAttribute("result", SUCCESS);
	        model.addAttribute("request",request);
	    }
	    catch (AdException e)
	    {
	        System.out.println("Exception: " + e.getMessage());
	        model.addAttribute("result", FAIL);
	    }
		
		//Get user
		model.addAttribute("userName",userName);		
		return "ajaxRequest";
	}
	
	@RequestMapping(value="ajaxAddProjectUser.htm", method=RequestMethod.GET)
	public String ajaxAddProjectUser(
			@RequestParam("projectUserID") int projectUserID, 
			@RequestParam("projectID") int projectID, 
			@RequestParam("projectUserRole") String projectRole,
			@RequestParam("projectUserName") String projectUserName,			
			Model model, HttpSession session) {
		if(!Authentication.islogin(session)) return "index";
		if(!Authentication.isUser((User)(session.getAttribute("user")))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");
			return "error";
		}
		if(!Authentication.isProjectAdmin(session, projectID)){
			model.addAttribute("errorMessage","Not authenticated to do this operation");
			return "error";
		}
		
		//Get user
		model.addAttribute("projectUserName",projectUserName);
		int SUCCESS = 1;
		int FAIL = 0;
		try
	    {
	        ProjectUserDAO puserDao = new ProjectUserDAO();
	        ProjectUser puser = puserDao.create(projectID, projectUserID, projectRole);
	        DAO.close();
	        model.addAttribute("ajaxUserName", projectUserName);
	        model.addAttribute("ajaxProjectID", projectID);
	        model.addAttribute("ajaxprojectUserRole", projectRole);
	        model.addAttribute("result", SUCCESS);
	    }
	    catch (AdException e)
	    {
	        System.out.println("Exception: " + e.getMessage());
	        model.addAttribute("result", FAIL);
	    }
		return "ajaxAddProjectUserResult";
	}
}
