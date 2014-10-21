package com.pmbox.pm;

import com.pmbox.pm.dao.DAO;
import com.pmbox.pm.dao.UserDAO;
import com.pmbox.pm.entity.User;
import com.pmbox.pm.exception.AdException;


public class UserDaoTest {
	
	public static void main(String[] args){
		User newUser = new User("test", "test", "user");
		System.out.println(newUser.getUserName());
		try
	    {
	        UserDAO userDao = new UserDAO();
	        userDao.create(newUser.getUserName(), newUser.getPassword(), newUser.getRole());
//	        DAO.close();
	    }
	    catch (AdException e)
	    {
	        System.out.println("Exception: " + e.getMessage());
	    }
	}
}
