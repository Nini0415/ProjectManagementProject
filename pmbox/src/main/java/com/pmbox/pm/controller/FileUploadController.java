package com.pmbox.pm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.pmbox.pm.dao.DAO;
import com.pmbox.pm.dao.UserFileDAO;
import com.pmbox.pm.entity.NewUserRequest;
import com.pmbox.pm.entity.User;
import com.pmbox.pm.entity.UserFile;
import com.pmbox.pm.exception.AdException;
import com.pmbox.pm.service.Authentication;
import com.pmbox.pm.service.DateOperation;
import com.pmbox.pm.service.FileUpload;
import com.pmbox.pm.service.FileValidator;

@Controller
public class FileUploadController {

	private static final int BUFFER_SIZE = 4096;

	@Autowired
	private FileValidator fileValidator;

//	@ModelAttribute("user")
//	public User construct() {
//		return new User();
//	}
//
//	@ModelAttribute("group")
//	public WorkGroup constructWorkGroup() {
//		return new WorkGroup();
//	} 
	  
//	 @RequestMapping("userFileUploadForm.htm", method=RequestMethod.GET)  
//	 public ModelAndView getUploadForm(  
//	   @ModelAttribute("uploadedFile") UploadedFile uploadedFile,  
//	   BindingResult result) {  
//	  return new ModelAndView("uploadForm");  
//	 }  
	  

	@RequestMapping(value="userFileUpload.htm", method=RequestMethod.POST)
	public String fileUploaded(
			@ModelAttribute("uploadedFile") FileUpload uploadedFile,
			BindingResult result, Principal principal, Model model,
			HttpSession session) {
		
		System.out.println("call FileUploadController fileUploaded");
		System.out.println("projectID for uploadedFile"+uploadedFile.getProjectID());
		InputStream inputStream = null;
		OutputStream outputStream = null;

		MultipartFile file = uploadedFile.getFile();
		fileValidator.validate(uploadedFile, result);

		String newName = FileUpload.rename(file.getOriginalFilename());

		//String saveFileName = "D:/uploads/" + newName;
		String saveFileName = "C:/uploads/" + newName;

		if (result.hasErrors()) {
			System.out.println("Form has error");
			model.addAttribute("message","Form has error");
			return "redirect:/userfile.htm";
		}
		User user = (User)session.getAttribute("user");
		try {
			inputStream = file.getInputStream();

			File newFile = new File(saveFileName);
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			outputStream = new FileOutputStream(newFile);
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			
			UserFile fileRecord = new UserFile(uploadedFile.getProjectID(), user.getUserID(), newName, DateOperation.getCurrentTime());
			UserFileDAO fileDAO = new UserFileDAO();
			fileDAO.create(fileRecord);
			//System.out.println("file uploaded successfully");
			model.addAttribute("message","file uploaded successfully!");
			DAO.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error occur during reading file");
			model.addAttribute("message","Error occur during reading file!");
		} catch (AdException e)
	    {
	        System.out.println("Exception: " + e.getMessage());
	        model.addAttribute("message","Error occur during writing in database!");
	    }
		System.out.println("redirect to user file");
		return "redirect:/userfile.htm";
	}

	@RequestMapping(value="download.htm",method = RequestMethod.GET)
	public String download(
			@RequestParam("fileName") String fileName,
			HttpServletRequest request,
            HttpServletResponse response,
            Model model,
            HttpSession session) throws IOException{
		if(!Authentication.islogin(session)) return "index";
		if(!Authentication.isUser((User)(session.getAttribute("user")))){
			model.addAttribute("errorMessage","Not authenticated to do this operation");				
			return "error";
		}
		//ServletContext context = request.getContextPath();
        System.out.println(fileName);
        String fullPath = "C:/uploads/"+fileName;
        
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);
         
        // get MIME type of the file
        String mimeType = "text/plain";
        
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
 
        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
 
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
 
        // get output stream of the response
        OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
 
        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
 
        inputStream.close();
        outStream.close();
        return "";
	}
//
//	@RequestMapping("/group-work/remove/{id}")
//	public String removeProject(@PathVariable int id, Principal principal) {
//		Project project = projectService.findOne(id);
//
//		String userName = principal.getName();
//		User user = userService.findOne(userName);
//		if(project.getWorkGroup().getId()!=user.getWorkGroup().getId()){
//
//			return "deny";
//		}
//		projectService.delete(project);
//		return "redirect:/group-work.html";
//	}
}
