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
import com.pmbox.pm.dao.DocumentDAO;
import com.pmbox.pm.dao.UserDAO;
import com.pmbox.pm.entity.Document;
import com.pmbox.pm.entity.Project;
import com.pmbox.pm.entity.User;
import com.pmbox.pm.exception.AdException;
import com.pmbox.pm.service.Authentication;
import com.pmbox.pm.service.DateOperation;

@Controller
public class DocumentController {
	@RequestMapping(value="document.htm", method=RequestMethod.GET)
	public String document( 
			@RequestParam("message") String message, 
			Model model, HttpSession session) {
		if(!Authentication.islogin(session)) return "index";
		if(!Authentication.isUser((User)(session.getAttribute("user")))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");				
			return "error";
		}
		session.setAttribute("currentMenu", "Document");
		List<Project> involvedProjects = (List<Project>)session.getAttribute("involvedProjects");
		System.out.println(involvedProjects.size());
		List<Document> documentList = new ArrayList<Document>();
		List<Project> docProjectList = new ArrayList<Project>();
		List<User> docAuthorList = new ArrayList<User>();
		try
	    {
			DocumentDAO documentDAO = new DocumentDAO();
			UserDAO userDAO = new UserDAO();
			for(Project project:involvedProjects){	
				System.out.println(project.getProjectID());
		        List<Document> testresult = documentDAO.getByProjectID(project.getProjectID());
		        for(Document t: testresult){
		        	documentList.add(t);
		        	docProjectList.add(project);
		        	User author = userDAO.get(t.getAuthorID());
		        	docAuthorList.add(author);
		        }
			}			
	        DAO.close();
	    }
	    catch (AdException e)
	    {
	        System.out.println("Exception: " + e.getMessage());
	    }
		model.addAttribute("documentList", documentList);
		model.addAttribute("docProjectList", docProjectList);
		model.addAttribute("docAuthorList", docAuthorList);
		model.addAttribute("chooseProjects", involvedProjects);
		return "document";
	}
	
	@RequestMapping(value="editDocument.htm", method=RequestMethod.POST)
	public String showEditDocumentPage( 
			@RequestParam("docProjectID") int docProjectID, 
			Model model, HttpSession session) {
		if(!Authentication.islogin(session)) return "index";
		if(!Authentication.isUser((User)(session.getAttribute("user")))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");				
			return "error";
		}
		model.addAttribute("docProjectID", docProjectID);
		return "documentEdit";
	}
	
	@RequestMapping(value="saveDocument.htm", method=RequestMethod.POST)
	public String saveDocument( 
			@RequestParam("documentName") String documentName, 
			@RequestParam("documentContent") String documentContent, 
			@RequestParam("docProjectID") int docProjectID, 
			Model model, HttpSession session) {
		if(!Authentication.islogin(session)) return "index";
		if(!Authentication.isUser((User)(session.getAttribute("user")))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");				
			return "error";
		}
		
		User user = (User)session.getAttribute("user");
		try
	    {
			DocumentDAO documentDAO = new DocumentDAO();
			Document document = documentDAO.create(docProjectID, user.getUserID(), DateOperation.getCurrentTime(), documentName, documentContent);
			model.addAttribute("message", "documentID:"+document.getDocumentID()+" has been created successfully!");
	        DAO.close();
	    }
	    catch (AdException e)
	    {
	        System.out.println("Exception: " + e.getMessage());
	        model.addAttribute("message", "Error occur during Document save!");
	    }
		return "redirect:/document.htm?message=";
	}
}
