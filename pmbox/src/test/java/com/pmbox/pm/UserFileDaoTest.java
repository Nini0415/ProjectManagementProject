package com.pmbox.pm;

import java.util.List;

import com.pmbox.pm.dao.DAO;
import com.pmbox.pm.dao.DocumentDAO;
import com.pmbox.pm.dao.TodoCommentDAO;
import com.pmbox.pm.dao.UserFileDAO;
import com.pmbox.pm.entity.Document;
import com.pmbox.pm.entity.TodoComment;
import com.pmbox.pm.entity.UserFile;
import com.pmbox.pm.exception.AdException;
import com.pmbox.pm.service.DateOperation;


public class UserFileDaoTest {
	
	public static void main(String[] args){
		int projectID = 2;
		UserFile file1 = new UserFile(projectID, 2, "20140422190735file1.txt", DateOperation.getCurrentTime());
		try
	    {
	        UserFileDAO fileDAO = new UserFileDAO();
	        fileDAO.create(file1);
	        DAO.close();
	    }
	    catch (AdException e)
	    {
	        System.out.println("Exception: " + e.getMessage());
	    }
	}
}
