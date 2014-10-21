package com.pmbox.pm.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.annotations.Check;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.pmbox.pm.dao.DAO;
import com.pmbox.pm.dao.UserDAO;
import com.pmbox.pm.entity.User;
import com.pmbox.pm.exception.AdException;
import com.pmbox.pm.service.Authentication;

@Controller
@RequestMapping(value="createAccount.htm")
public class UserManagementController {	
	@ModelAttribute("newUser")
	public User construct(){
		return new User();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createNewAccount(
			@Valid @ModelAttribute("newUser") User newUser,
			BindingResult result,
			SessionStatus status,
//			@RequestParam("name") String name, 
//			@RequestParam("password") String password, 
//			@RequestParam("role") String role, 
			Model model, HttpSession session) {
		if(!Authentication.islogin(session)) return "index";
		if(!Authentication.isAdmin((User)(session.getAttribute("user")))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");
			return "error";
		}
		//Validation
		if(result.hasErrors()){
			return "createAccount";
		}
		System.out.println(newUser.getUserName());
		//System.out.println(newUser.getUserID()+""+newUser.getUserName());
		try
	    {
	        UserDAO userDao = new UserDAO();
//	        userDao.create(name, password, role);	   
	        userDao.create(newUser);
	        DAO.close();
	        status.setComplete();
	        model.addAttribute("message","Account "+newUser.getUserName()+" has been created successfully");
	        return "createAccount";
	    }
	    catch (AdException e)
	    {
	        System.out.println("Exception: " + e.getMessage());
	    }		
		model.addAttribute("message","Error occur, please create again!");
		return "createAccount";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String createNewAccountError(Model model, HttpSession session) {
		if(!Authentication.islogin(session)) return "index";
		if(!Authentication.isAdmin((User)(session.getAttribute("user")))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");
			return "error";
		}
		return "createAccount";
	}
}
