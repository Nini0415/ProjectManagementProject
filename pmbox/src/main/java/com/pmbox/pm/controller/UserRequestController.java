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
import com.pmbox.pm.dao.NewUserRequestDAO;
import com.pmbox.pm.dao.TaskDAO;
import com.pmbox.pm.dao.TodoCommentDAO;
import com.pmbox.pm.dao.TodoDAO;
import com.pmbox.pm.dao.UserDAO;
import com.pmbox.pm.entity.NewUserRequest;
import com.pmbox.pm.entity.Task;
import com.pmbox.pm.entity.Todo;
import com.pmbox.pm.entity.TodoComment;
import com.pmbox.pm.entity.User;
import com.pmbox.pm.exception.AdException;
import com.pmbox.pm.service.Authentication;
import com.pmbox.pm.service.DateOperation;
import com.pmbox.pm.service.TaskTodo;

@Controller
public class UserRequestController {
	@RequestMapping(value="userRequests.htm", method=RequestMethod.GET)
	public String todoHome(
			Model model, HttpSession session) {
		//Check authentication
		if(!Authentication.islogin(session)) return "index";
		if(!Authentication.isUser((User)(session.getAttribute("user")))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");
			return "error";
		}
		User user = (User)session.getAttribute("user");
		try
	    {
			NewUserRequestDAO requestDAO = new NewUserRequestDAO();
	        List<NewUserRequest> testresult = requestDAO.getBySenderID(user.getUserID());
	        DAO.close();
	        model.addAttribute("myRequests", testresult);
	    }
	    catch (AdException e)
	    {
	        System.out.println("Exception: " + e.getMessage());
	        model.addAttribute("message", "Error occur during communication with database, click request to refresh this page!");
	    }
		session.setAttribute("currentMenu", "Requests");
		return "userRequests";
	}
}
