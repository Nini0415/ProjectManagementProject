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
import com.pmbox.pm.dao.UserFileDAO;
import com.pmbox.pm.entity.Document;
import com.pmbox.pm.entity.Project;
import com.pmbox.pm.entity.User;
import com.pmbox.pm.entity.UserFile;
import com.pmbox.pm.exception.AdException;
import com.pmbox.pm.service.Authentication;
import com.pmbox.pm.service.DateOperation;

@Controller
public class UserFileController {
	@RequestMapping(value="userfile.htm", method=RequestMethod.GET)
	public String document( 
			@RequestParam("message") String message, 
			Model model, HttpSession session) {
		if(!Authentication.islogin(session)) return "index";
		if(!Authentication.isUser((User)(session.getAttribute("user")))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");				
			return "error";
		}
		session.setAttribute("currentMenu", "File");
		List<Project> involvedProjects = (List<Project>)session.getAttribute("involvedProjects");
		//System.out.println(involvedProjects.size());
		List<UserFile> fileList = new ArrayList<UserFile>();
		List<Project> fileProjectList = new ArrayList<Project>();
		List<User> fileUploaderList = new ArrayList<User>();
		try
	    {
			UserFileDAO fileDAO = new UserFileDAO();
			UserDAO userDAO = new UserDAO();
			for(Project project:involvedProjects){	
				//System.out.println(project.getProjectID());
		        List<UserFile> testresult = fileDAO.getByProjectID(project.getProjectID());
		        for(UserFile t: testresult){
		        	fileList.add(t);
		        	fileProjectList.add(project);
		        	User uploader = userDAO.get(t.getUploaderID());
		        	fileUploaderList.add(uploader);
		        }
			}			
	        DAO.close();
	    }
	    catch (AdException e)
	    {
	        System.out.println("Exception: " + e.getMessage());
	    }
		model.addAttribute("fileList", fileList);
		model.addAttribute("fileProjectList", fileProjectList);
		model.addAttribute("fileUploaderList", fileUploaderList);
		//model.addAttribute("chooseProjects", involvedProjects);
		return "userFile";
	}
	
//	@RequestMapping(value="editDocument.htm", method=RequestMethod.POST)
//	public String showEditDocumentPage( 
//			@RequestParam("docProjectID") int docProjectID, 
//			Model model, HttpSession session) {
//		if(!Authentication.islogin(session)) return "index";
//		if(!Authentication.isUser((User)(session.getAttribute("user")))){
//			model.addAttribute("errorMessage","Not authenticated to do this operation");				
//			return "error";
//		}
//		model.addAttribute("docProjectID", docProjectID);
//		return "documentEdit";
//	}
//	
//	@RequestMapping(value="saveDocument.htm", method=RequestMethod.POST)
//	public String saveDocument( 
//			@RequestParam("documentName") String documentName, 
//			@RequestParam("documentContent") String documentContent, 
//			@RequestParam("docProjectID") int docProjectID, 
//			Model model, HttpSession session) {
//		if(!Authentication.islogin(session)) return "index";
//		if(!Authentication.isUser((User)(session.getAttribute("user")))){
//			model.addAttribute("errorMessage","Not authenticated to do this operation");				
//			return "error";
//		}
//		
//		User user = (User)session.getAttribute("user");
//		try
//	    {
//			DocumentDAO documentDAO = new DocumentDAO();
//			Document document = documentDAO.create(docProjectID, user.getUserID(), DateOperation.getCurrentTime(), documentName, documentContent);
//			model.addAttribute("message", "documentID:"+document.getDocumentID()+" has been created successfully!");
//	        DAO.close();
//	    }
//	    catch (AdException e)
//	    {
//	        System.out.println("Exception: " + e.getMessage());
//	        model.addAttribute("message", "Error occur during Document save!");
//	    }
//		return "redirect:/document.htm?message=";
//	}
}
