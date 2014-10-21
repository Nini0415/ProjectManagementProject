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
import com.pmbox.pm.dao.UserDAO;
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
public class CalendarController {
	//Add task
	@RequestMapping(value="calendar.htm", method=RequestMethod.GET)
	public String calendar(
//			@RequestParam("name") String name, 
			Model model, HttpSession session) {
		//Authentication!!!!!
		if(!Authentication.islogin(session)) return "index";
		if(!Authentication.isUser((User)(session.getAttribute("user")))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");
			return "error";
		}
		
		User user = (User)session.getAttribute("user");
		
		// get all task that user is assignee
		try
	    {
	        TodoDAO todoDAO = new TodoDAO();
	        TaskDAO taskDAO = new TaskDAO();
	        List<Task> taskList = new ArrayList<Task>();
	        List<Todo> todosresultList = todoDAO.getByAssigneeID(user.getUserID());
	        for(Todo todo: todosresultList){
	        	Task task = taskDAO.get(todo.getTaskID());
	        	taskList.add(task);
	        }
	        DAO.close();
	        model.addAttribute("todoList", todosresultList);
	        model.addAttribute("taskList", taskList);
	    }
	    catch (AdException e)
	    {
	        System.out.println("Exception: " + e.getMessage());
	    }
		//get task and todo list end
		
		session.setAttribute("currentMenu", "Calendar");
		return"calendar";
	}
}
