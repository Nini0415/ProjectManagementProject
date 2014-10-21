package com.pmbox.pm;

import java.util.List;

import com.pmbox.pm.dao.DAO;
import com.pmbox.pm.dao.NewUserRequestDAO;
import com.pmbox.pm.entity.NewUserRequest;
import com.pmbox.pm.exception.AdException;
import com.pmbox.pm.service.DateOperation;
import com.pmbox.pm.service.RandomString;


public class NewUserRequestDaoTest {
	
	public static void main(String[] args){
		int requestID = 1;
		int adminID = 1;
		RandomString rS = new RandomString(8);
        String initialPassword = rS.nextString();
		try
	    {
			NewUserRequestDAO requestDAO = new NewUserRequestDAO();	        
			NewUserRequest request = requestDAO.get(requestID);
			request.setAdminID(adminID);
	        request.setFinishDate(DateOperation.getCurrentTime());
	        request.setInitialPass(initialPassword);
	        requestDAO.save(request);
	        DAO.close();
	    }
	    catch (AdException e)
	    {
	        System.out.println("Exception: " + e.getMessage());
	    }
	}
}
