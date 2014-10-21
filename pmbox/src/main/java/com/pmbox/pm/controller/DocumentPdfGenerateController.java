package com.pmbox.pm.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pmbox.pm.dao.DAO;
import com.pmbox.pm.dao.DocumentDAO;
import com.pmbox.pm.entity.Document;
import com.pmbox.pm.entity.User;
import com.pmbox.pm.exception.AdException;
import com.pmbox.pm.service.Authentication;

@Controller
public class DocumentPdfGenerateController {

	@RequestMapping(value = "document**.pdf", method = RequestMethod.GET)
	public ModelAndView doProcess( 
			@RequestParam("documentID") int documentID, 
			@RequestParam("projectName") String projectName, 
			@RequestParam("authorName") String authorName, 
			ModelMap model, HttpSession session) {
		if(!Authentication.islogin(session)) return new ModelAndView("index");
		if(!Authentication.isUser((User)(session.getAttribute("user")))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");				
			return new ModelAndView("error");
		}		
		System.out.println("catch request report.pdf");	
		
		try
	    {
	        DocumentDAO documentDAO = new DocumentDAO();
	        Document doc = documentDAO.get(documentID);
	        DAO.close();
	        model.addAttribute("message", "");
	        model.addAttribute("projectName", projectName);
	        model.addAttribute("authorName", authorName);
	        model.addAttribute("docName", doc.getName());
	        model.addAttribute("docContent", doc.getContent());
	    }
	    catch (AdException e)
	    {
	        System.out.println("Exception: " + e.getMessage());
	        model.addAttribute("message", "error occur, please go back and view this document again.");
	        model.addAttribute("projectName", "");
	        model.addAttribute("authorName", "");
	        model.addAttribute("docName", "");
	        model.addAttribute("docContent", "");
	    }
		return new ModelAndView(new DocumentPdfView(), model);
	}
}
