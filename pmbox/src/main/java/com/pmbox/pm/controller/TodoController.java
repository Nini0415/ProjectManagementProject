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
import com.pmbox.pm.dao.TodoCommentDAO;
import com.pmbox.pm.dao.TodoDAO;
import com.pmbox.pm.dao.UserDAO;
import com.pmbox.pm.entity.Todo;
import com.pmbox.pm.entity.TodoComment;
import com.pmbox.pm.entity.User;
import com.pmbox.pm.exception.AdException;
import com.pmbox.pm.service.Authentication;
import com.pmbox.pm.service.DateOperation;


@Controller
public class TodoController {
	@RequestMapping(value="todo.htm", method=RequestMethod.GET)
	public String todoHome(@RequestParam("projectID") int projectID, 
			@RequestParam("taskName") String taskName,
			@RequestParam("todoID") int todoID,
			@RequestParam("message") String message,
			Model model, HttpSession session) {
		//Check authentication
		if(!Authentication.islogin(session)) return "index";
		if(!Authentication.isUser((User)(session.getAttribute("user")))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");
			return "error";
		}
//		if(!(Authentication.isProjectAdmin(session, projectID) ||
//				Authentication.isProjectMember(session, projectID)||
//				Authentication.isProjectClient(session, projectID))){
//			model.addAttribute("errorMessage","Not authenticated to do this operation");
//			return "error";
//		}			
		if(Authentication.isProjectAdmin(session, projectID) ||
				Authentication.isProjectMember(session, projectID)){
			model.addAttribute("projectID",projectID);			
			//Add commentList
			try
			{
			    TodoDAO todoDAO = new TodoDAO();
			    TodoCommentDAO todoCommentDAO = new TodoCommentDAO();
			    UserDAO userDAO = new UserDAO();
			    Todo todo = todoDAO.get(todoID);
			    User creater = userDAO.get(todo.getCreaterID());
			    User assignee = null;
			    int assigneeID = todo.getAssigneeID();
			    if(assigneeID > 0){
			    	assignee = userDAO.get(assigneeID);
			    }
			    List<TodoComment> testresult = todoCommentDAO.getByTodoID(todoID);
			    List<User> commenters = new ArrayList<User>();
			    // add commenter in commenters list
			    if(testresult.size() > 0){
			    	for(TodoComment comment: testresult){
			        	int commenterID = comment.getCommenterID();
			        	User user = userDAO.get(commenterID);
			        	commenters.add(user);
			        }
			    }	
			    DAO.close();
			    if(message.equals("ok")){message = "";}
			    if(!message.equals("ok")){
			    	model.addAttribute("message", message);}
			    model.addAttribute("taskName",taskName);
			    model.addAttribute("todo",todo);
			    model.addAttribute("creater",creater);
			    model.addAttribute("assignee",assignee);
			    model.addAttribute("comments", testresult);		
			    model.addAttribute("commenters", commenters);		    
			    return "todo";
			}
			catch (AdException e)
			{
			    System.out.println("Exception: " + e.getMessage());
			}		
			
			model.addAttribute("message","Error occur, please operate again!");
			model.addAttribute("projectID", projectID);
			return "redirect:/project.htm";
		}else if(Authentication.isProjectClient(session, projectID)){
			model.addAttribute("projectID",projectID);			
			//Add commentList
			try
			{
			    TodoDAO todoDAO = new TodoDAO();
			    TodoCommentDAO todoCommentDAO = new TodoCommentDAO();
			    UserDAO userDAO = new UserDAO();
			    Todo todo = todoDAO.get(todoID);
			    User creater = userDAO.get(todo.getCreaterID());
			    User assignee = null;
			    int assigneeID = todo.getAssigneeID();
			    if(assigneeID > 0){
			    	assignee = userDAO.get(assigneeID);
			    }
			    List<TodoComment> testresult = todoCommentDAO.getByTodoID(todoID);
			    List<User> commenters = new ArrayList<User>();
			    // add commenter in commenters list
			    if(testresult.size() > 0){
			    	for(TodoComment comment: testresult){
			        	int commenterID = comment.getCommenterID();
			        	User user = userDAO.get(commenterID);
			        	commenters.add(user);
			        }
			    }	
			    DAO.close();
			    if(message.equals("ok")){message = "";}
			    if(!message.equals("ok")){
			    	model.addAttribute("message", message);}
			    model.addAttribute("taskName",taskName);
			    model.addAttribute("todo",todo);
			    model.addAttribute("creater",creater);
			    model.addAttribute("assignee",assignee);
			    model.addAttribute("comments", testresult);		
			    model.addAttribute("commenters", commenters);		    
			    return "todoClient";
			}
			catch (AdException e)
			{
			    System.out.println("Exception: " + e.getMessage());
			}		
			
			model.addAttribute("message","Error occur, please operate again!");
			model.addAttribute("projectID", projectID);
			return "redirect:/project.htm";
		}else{
			model.addAttribute("errorMessage","Not authenticated to do this operation");
			return "error";
		}

	}
		
	//Add task
	@RequestMapping(value="addTodo.htm", method=RequestMethod.POST)
	public String addTodo(@RequestParam("projectID") int projectID, 
			@RequestParam("taskID") int taskID, 
			@RequestParam("description") String description, Model model, HttpSession session) {
		if(!Authentication.islogin(session)) return "index";
		if(!Authentication.isUser((User)(session.getAttribute("user")))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");				
			return "error";
		}
		// Check project ROLE
		if(!(Authentication.isProjectAdmin(session, projectID) ||
				Authentication.isProjectMember(session, projectID))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");				
			return "error";
		}
		//how to Check if the passed values is valid? Intercepter?
		User user = (User)session.getAttribute("user");
		try
		{
		    TodoDAO todoDao = new TodoDAO();		        
		    Todo todo = todoDao.create(taskID, user.getUserID(), DateOperation.getCurrentTime(), description);
		    model.addAttribute("message","TodoID: "+todo.getTodoID()+" has been created successfully");
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
	
	//Add task
	@RequestMapping(value="updateTodo.htm", method=RequestMethod.POST)
	public String updateTodo(@RequestParam("projectID") int projectID, 
			@RequestParam("todoID") int todoID, 
			@RequestParam("description") String description, 
			@RequestParam("assignee") String assigneeName, 
			@RequestParam("startDate") String startDate, 
			@RequestParam("dueDate") String dueDate,
			@RequestParam("taskName") String taskName,
			Model model, HttpSession session) {
		if(!Authentication.islogin(session)) return "index";
		if(!Authentication.isUser((User)(session.getAttribute("user")))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");				
			return "error";
		}
		
		// Check project ROLE
		if(!(Authentication.isProjectAdmin(session, projectID) ||
				Authentication.isProjectMember(session, projectID))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");				
			return "error";
		}
		
		//how to Check if the passed values is valid? Intercepter?
		User user = (User)session.getAttribute("user");
		try
		{
		    TodoDAO todoDao = new TodoDAO();	
		    UserDAO userDAO = new UserDAO();
		    User assignee = userDAO.get(assigneeName);
		    Todo todo = todoDao.get(todoID);
		    todo.setDescription(description);
		    todo.setAssigneeID(assignee.getUserID());
		    todo.setStartDate(startDate);
		    todo.setDueDate(dueDate);
		    todoDao.save(todo);
		    DAO.close();
		    model.addAttribute("message","TodoID: "+todo.getTodoID()+" has been updated successfully");
		}
		catch (AdException e)
		{
		    System.out.println("Exception: " + e.getMessage());

			model.addAttribute("message","Error occur, please edit again!");
		}
		model.addAttribute("projectID", projectID);
		model.addAttribute("taskName", taskName);
		model.addAttribute("todoID", todoID);
		return "redirect:/todo.htm";
		
	}
		
	//Delete task
	@RequestMapping(value="deleteTodo.htm", method=RequestMethod.GET)
	public String deleteTodo(@RequestParam("projectID") int projectID, 
			@RequestParam("taskID") int taskID,@RequestParam("todoID") int todoID,
			Model model, HttpSession session) {
		if(!Authentication.islogin(session)) return "index";
		if(!Authentication.isUser((User)(session.getAttribute("user")))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");
			return "error";
		}
		if(!(Authentication.isProjectAdmin(session, projectID)||
				Authentication.isProjectMember(session, projectID))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");
			return "error";
		}
		
		//add member and client project homepage
		
		//how to Check if the passed values is valid? Intercepter?			
		try
		{
		    TodoDAO todoDao = new TodoDAO();
		    Todo todo = todoDao.get(todoID);
		    int deletetodoID = todo.getTodoID();
		    todoDao.delete(todo);
		    model.addAttribute("message","Todo: "+deletetodoID+" has been deleted successfully");
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
