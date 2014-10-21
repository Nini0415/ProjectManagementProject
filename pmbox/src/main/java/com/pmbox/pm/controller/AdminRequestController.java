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
import com.pmbox.pm.dao.UserDAO;
import com.pmbox.pm.entity.NewUserRequest;
import com.pmbox.pm.entity.User;
import com.pmbox.pm.exception.AdException;
import com.pmbox.pm.service.Authentication;
import com.pmbox.pm.service.DateOperation;
import com.pmbox.pm.service.RandomString;

@Controller
public class AdminRequestController {
	@RequestMapping(value="adminRequests.htm", method=RequestMethod.GET)
	public String adminRequests(
			@RequestParam("message") String message,
			Model model, HttpSession session){
		if(!Authentication.islogin(session)) return "index";
		if(!Authentication.isAdmin((User)(session.getAttribute("user")))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");
			return "error";
		}
		session.setAttribute("currentMenu", "Requests");
		if(!message.equals("OK")){
			model.addAttribute("message", message);
		}
		//Get all requests and all sender info from database
		try
	    {
			NewUserRequestDAO requestDAO = new NewUserRequestDAO();
			UserDAO userDAO = new UserDAO();
	        List<NewUserRequest> testresult = requestDAO.getOpenRequests();
	        List<User> senders = new ArrayList<User>();
	        for(NewUserRequest t: testresult){
	        	User sender = userDAO.get(t.getSenderID());
	        	senders.add(sender);
	        }
	        DAO.close();
	        model.addAttribute("openRequests", testresult);
	        model.addAttribute("senders", senders);
	    }
	    catch (AdException e)
	    {
	        System.out.println("Exception: " + e.getMessage());
	        model.addAttribute("message", "Error occur during communication with database, click request to refresh this page!");
	    }
		
		return "adminRequests";
	}
	
	@RequestMapping(value="createForRequest.htm", method=RequestMethod.GET)
	public String createUserForRequest(
			@RequestParam("requestID") int requestID,
			Model model, HttpSession session){
		if(!Authentication.islogin(session)) return "index";
		if(!Authentication.isAdmin((User)(session.getAttribute("user")))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");
			return "error";
		}
		User admin = (User)session.getAttribute("user");
		session.setAttribute("currentMenu", "Requests");
		//Get all requests and all sender info from database
		try
	    {
			NewUserRequestDAO requestDAO = new NewUserRequestDAO();
			UserDAO userDAO = new UserDAO();
	        NewUserRequest request = requestDAO.get(requestID);
	        
	        //create User
	        RandomString rS = new RandomString(8);
	        String initialPassword = rS.nextString();
	        User newUser = userDAO.create(request.getRequestUserName(), initialPassword, "user");
	        
	        //update request record
	        // edit request update DAO
	        request.setAdminID(admin.getUserID());
	        request.setFinishDate(DateOperation.getCurrentTime());
	        request.setInitialPass(initialPassword);
	        requestDAO.save(request);
	        
	        DAO.close();
	        model.addAttribute("message", "UserID:"+newUser.getUserID()+" has been created successfully!");
	    }
	    catch (AdException e)
	    {
	        System.out.println("Exception: " + e.getMessage());
	        model.addAttribute("message", "Error occur during communication with database, create again!");
	    }
		
		return "redirect:/adminRequests.htm";
	}
}
