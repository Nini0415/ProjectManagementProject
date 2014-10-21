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
import com.pmbox.pm.dao.TaskDAO;
import com.pmbox.pm.dao.TodoCommentDAO;
import com.pmbox.pm.dao.TodoDAO;
import com.pmbox.pm.dao.UserDAO;
import com.pmbox.pm.entity.Task;
import com.pmbox.pm.entity.Todo;
import com.pmbox.pm.entity.TodoComment;
import com.pmbox.pm.entity.User;
import com.pmbox.pm.exception.AdException;
import com.pmbox.pm.service.Authentication;
import com.pmbox.pm.service.DateOperation;
import com.pmbox.pm.service.TaskTodo;

@Controller
public class CommentController {
	//Add task
	@RequestMapping(value="addTodoComment.htm", method=RequestMethod.POST)
	public String addTodoComment(
			@RequestParam("projectID") int projectID,
			@RequestParam("taskName") String taskName,
			@RequestParam("todoID") int todoID, 
			@RequestParam("comment") String comment, 
			Model model, HttpSession session) {
		if(!Authentication.islogin(session)) return "index";
		if(!Authentication.isUser((User)(session.getAttribute("user")))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");				
			return "error";
		}
		// Check project ROLE
		if(!(Authentication.isProjectAdmin(session, projectID) ||
				Authentication.isProjectMember(session, projectID) ||
				Authentication.isProjectClient(session, projectID))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");				
			return "error";
		}
		//how to Check if the passed values is valid? Intercepter?
		User user = (User)session.getAttribute("user");
		// save comment in DB
		try
		{
		    TodoCommentDAO todoCommentDAO = new TodoCommentDAO();
		    TodoComment newcomment = todoCommentDAO.create(todoID, user.getUserID(), 
		    		DateOperation.getCurrentTime(), comment);
		    
		    //model.addAttribute("message","commentID: "+newcomment.getCommentID()+" has been created successfully");
		    model.addAttribute("message","");
		    DAO.close();
		}
		catch (AdException e)
		{
		    System.out.println("Exception: " + e.getMessage());
			model.addAttribute("message","Error occur, please create again!");
		}
		model.addAttribute("projectID", projectID);
		model.addAttribute("taskName", taskName);
		model.addAttribute("todoID", todoID);
		return "redirect:/todo.htm";	
	}
			
	//Delete TodoComment
	@RequestMapping(value="deleteComment.htm", method=RequestMethod.GET)
	public String deleteComment(
			@RequestParam("projectID") int projectID,
			@RequestParam("taskName") String taskName,
			@RequestParam("todoID") int todoID, 
			@RequestParam("commentID") int commentID, 
			Model model, HttpSession session) {
		if(!Authentication.islogin(session)) return "index";
		if(!Authentication.isUser((User)(session.getAttribute("user")))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");
			return "error";
		}
		
		TodoComment comment = null;
		//get comment object
		try
		{
			TodoCommentDAO todoCommentDAO = new TodoCommentDAO();
		    comment = todoCommentDAO.get(commentID);
		    DAO.close();
		}
		catch (AdException e)
		{
		    System.out.println("Exception: " + e.getMessage());
		    model.addAttribute("message","Error occur, please delete again!");
			model.addAttribute("projectID", projectID);
			model.addAttribute("taskName", taskName);
			model.addAttribute("todoID", todoID);
			return "redirect:/todo.htm";
		}
		
		if((comment == null) || (!Authentication.isCommenter(session, comment))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");
			return "error";
		}
		
		//add member and client project homepage
		
		//how to Check if the passed values is valid? Intercepter?			
		try
		{
			TodoCommentDAO todoCommentDAO = new TodoCommentDAO();
			todoCommentDAO.delete(comment);		    
		    DAO.close();
		    model.addAttribute("message","CommentID: "+comment.getCommentID()+" has been deleted successfully");
		    model.addAttribute("projectID", projectID);
		    model.addAttribute("taskName", taskName);
			model.addAttribute("todoID", todoID);
			return "redirect:/todo.htm";
		}
		catch (AdException e)
		{
		    System.out.println("Exception: " + e.getMessage());
		}
		model.addAttribute("message","Error occur, please delete again!");
		model.addAttribute("projectID", projectID);
		model.addAttribute("taskName", taskName);
		model.addAttribute("todoID", todoID);
		return "redirect:/todo.htm";
	}
}
