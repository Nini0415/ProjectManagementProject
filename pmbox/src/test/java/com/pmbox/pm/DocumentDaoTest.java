package com.pmbox.pm;

import java.util.List;

import com.pmbox.pm.dao.DAO;
import com.pmbox.pm.dao.DocumentDAO;
import com.pmbox.pm.dao.TodoCommentDAO;
import com.pmbox.pm.entity.Document;
import com.pmbox.pm.entity.TodoComment;
import com.pmbox.pm.exception.AdException;


public class DocumentDaoTest {
	
	public static void main(String[] args){
		int projectID = 6;
		Document doc1 = new Document(6,2, "04/21/2014", "bb's planning", "bb's plannings content");
		Document doc2 = new Document(6,2, "04/21/2014", "bb's project closing record", "bb's project closing record content");
		try
	    {
	        DocumentDAO documentDAO = new DocumentDAO();
	        
	        documentDAO.create(doc1.getProjectID(),doc1.getAuthorID(),doc1.getCreateDate(), doc1.getName(), doc1.getContent());
	        documentDAO.create(doc2.getProjectID(),doc2.getAuthorID(),doc2.getCreateDate(), doc2.getName(), doc2.getContent());
	        List<Document> testresult = documentDAO.getByProjectID(projectID);
	        for(Document t: testresult){
	        	System.out.println(t);
	        }
	        DAO.close();
	    }
	    catch (AdException e)
	    {
	        System.out.println("Exception: " + e.getMessage());
	    }
	}
}
