package com.pmbox.pm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pmbox.pm.dao.DAO;
import com.pmbox.pm.dao.ProjectDAO;
import com.pmbox.pm.dao.ProjectUserDAO;
import com.pmbox.pm.dao.UserDAO;
import com.pmbox.pm.entity.Project;
import com.pmbox.pm.entity.ProjectUser;
import com.pmbox.pm.entity.User;
import com.pmbox.pm.exception.AdException;
import com.pmbox.pm.service.Authentication;

@Controller
public class LoginController {
	@RequestMapping(value="home.htm", method=RequestMethod.POST)
	public String login(@RequestParam("name") String name, @RequestParam("password") String password, 
			Model model, HttpSession session) {
		try
	    {
	        UserDAO userDao = new UserDAO();
	        User user = null;
	        user = userDao.get(name);
	        if(user == null){
	        	model.addAttribute("message","User does not exist, please login again!");
	        	return "index";
	        }
	        if(!user.getPassword().equals(password)){
	        	model.addAttribute("message", "Password error, please login again!");
	        	return "index";
	        }
	        
	        session.setAttribute("user", user);
	        
	        if(user.getRole().equals("user")){	
	        	//get user's related projects
	        	List<ProjectUser> pUsers = getProjectUsers((User)(session.getAttribute("user")));
	    		List<Project> projects = getProjects(pUsers);
	    		session.setAttribute("currentPUs", pUsers);
	    		session.setAttribute("involvedProjects", projects);
	        	session.setAttribute("currentMenu", "Projects");
	        	return "userHome";	        	
	        }
	        if(user.getRole().equals("admin")){
	        	session.setAttribute("currentMenu", "Users");
	        	return "adminHome";	        	
	        }	
	        DAO.close();
	    }
	    catch (AdException e)
	    {
	        System.out.println("Exception: " + e.getMessage());
	    }		
		model.addAttribute("message","Error occur, please login again!");
		return "index";
	}
	
	@RequestMapping(value="login.htm", method=RequestMethod.GET)
	public String logout(Model model, HttpSession session){
		session.setAttribute("user", null);
		session.setAttribute("currentP", null);
		session.setAttribute("currentPUs", null);
		session.setAttribute("currentMenu", null);
		session.setAttribute("involvedProjects", null);		
		return "index";
	}
	
	@RequestMapping(value="home.htm", method=RequestMethod.GET)
	public String userHome(@RequestParam("message") String message, Model model, HttpSession session) {
		if(!Authentication.islogin(session)) return "index";
		if(!Authentication.isUser((User)(session.getAttribute("user")))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");
			return "error";
		}
		if(!message.equals("start")){
			model.addAttribute("message", message);
		}
		List<ProjectUser> pUsers = getProjectUsers((User)(session.getAttribute("user")));
		List<Project> projects = getProjects(pUsers);
		session.setAttribute("currentPUs", pUsers);
		session.setAttribute("involvedProjects", projects);
		session.setAttribute("currentMenu", "Projects");		
		return "userHome";
	}
	
	@RequestMapping(value="adminHome.htm", method=RequestMethod.GET)
	public String adminHome(Model model, HttpSession session) {
		if(!Authentication.islogin(session)) return "index";
		if(!Authentication.isAdmin((User)(session.getAttribute("user")))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");
			return "error";
		}
		session.setAttribute("currentMenu", "Users");
		return "adminHome";
	}
	
	private List<ProjectUser> getProjectUsers(User user){
		List<ProjectUser> pusers = null;
		try
		{
			pusers = new ArrayList<ProjectUser>();
		    ProjectUserDAO projectUDao = new ProjectUserDAO();	
		    pusers = projectUDao.getProjectsByUserID(user.getUserID());
		    DAO.close();
		}
		catch (AdException e)
		{
		    System.out.println("Exception: " + e.getMessage());
		}
		
		return pusers;
	}
	private List<Project> getProjects(List<ProjectUser> pusers){
		List<Project> projects = null;
		try
		{
			projects = new ArrayList<Project>();
		    ProjectDAO projectDAO = new ProjectDAO();
		    for(ProjectUser pu: pusers){
		    	Project p = projectDAO.get(pu.getProjectID());
		    	projects.add(p);
		    }
		    DAO.close();
		}
		catch (AdException e)
		{
		    System.out.println("Exception: " + e.getMessage());
		}
		
		return projects;
	}
}
