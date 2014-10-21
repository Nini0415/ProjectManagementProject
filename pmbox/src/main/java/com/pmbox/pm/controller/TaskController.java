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
import com.pmbox.pm.dao.TaskDAO;
import com.pmbox.pm.dao.TodoDAO;
import com.pmbox.pm.entity.Project;
import com.pmbox.pm.entity.ProjectUser;
import com.pmbox.pm.entity.Task;
import com.pmbox.pm.entity.Todo;
import com.pmbox.pm.entity.User;
import com.pmbox.pm.exception.AdException;
import com.pmbox.pm.service.Authentication;
import com.pmbox.pm.service.DateOperation;
import com.pmbox.pm.service.TaskTodo;

@Controller
public class TaskController {
	//Add task
	@RequestMapping(value="addTask.htm", method=RequestMethod.POST)
	public String addTask(
			@RequestParam("projectID") int projectID, 
			@RequestParam("name") String name, 
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
		//how to Check if the passed values is valid? Intercepter?
		try
		{
		    TaskDAO taskDao = new TaskDAO();		        
		    Task newtask = taskDao.create(projectID, name, DateOperation.getCurrentTime());
		    model.addAttribute("message","Task "+name+" has been created successfully");
		    model.addAttribute("projectID", projectID);
		    DAO.close();
		    return "redirect:/project.htm";
		}
		catch (AdException e)
		{
		    System.out.println("Exception: " + e.getMessage());
		}
		model.addAttribute("message","Error occur, please create again!");
		model.addAttribute("projectID", projectID);
		return "redirect:/project.htm";
		
	}
	
	//Delete task
	@RequestMapping(value="deleteTask.htm", method=RequestMethod.GET)
	public String deleteTask(@RequestParam("projectID") int projectID, @RequestParam("taskID") int taskID,Model model, HttpSession session) {
		if(!Authentication.islogin(session)) return "index";
		if(!Authentication.isUser((User)(session.getAttribute("user")))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");
			return "error";
		}
		if(!Authentication.isProjectAdmin(session, projectID)){
			model.addAttribute("errorMessage","Not authenticated to do this operation");
			return "error";
		}
		
		//add member and client project homepage
		
		//how to Check if the passed values is valid? Intercepter?			
		try
	    {
	        TaskDAO taskDao = new TaskDAO();
	        Task task = taskDao.get(taskID);
	        String name = task.getName();
	        taskDao.delete(task);
	        model.addAttribute("message","Task "+name+" has been deleted successfully");
	        DAO.close();
	        model.addAttribute("projectID", projectID);
			return "redirect:/project.htm";
	    }
	    catch (AdException e)
	    {
	        System.out.println("Exception: " + e.getMessage());
	    }
		model.addAttribute("message","Error occur, please delete again!");
		model.addAttribute("projectID", projectID);
		return "redirect:/project.htm";
	}
}
