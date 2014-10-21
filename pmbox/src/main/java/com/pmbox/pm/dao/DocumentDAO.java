package com.pmbox.pm.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.pmbox.pm.entity.Document;
import com.pmbox.pm.entity.Todo;
import com.pmbox.pm.entity.TodoComment;
import com.pmbox.pm.exception.AdException;



public class DocumentDAO extends DAO {
	public DocumentDAO(){
		
	}
	
	// get all comments with todoID
	public List<Document> getByProjectID(int projectID) throws AdException{
		try{
			begin();
			Query q = getSession().createQuery("from Document where projectID = :projectID");
			q.setString("projectID", String.valueOf(projectID));
			
			List rawlist = q.list();
			commit();
			List<Document> resultlist = new ArrayList<Document>();
			for(Object o: rawlist){
				resultlist.add((Document)o);
			}
			return resultlist;
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not get documents for projectID:" + projectID, e);
		}
	}
	
	//get document with documentID
	public Document get(int documentID) throws AdException{
		try{
			begin();
			Query q = getSession().createQuery("from Document where documentID = :documentID");
			q.setString("documentID", String.valueOf(documentID));
			Document document = (Document)q.uniqueResult();
			commit();
			return document;
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not get document for documentID:" + documentID, e);
		}
	}

	public Document create(int projectID, int authorID, String createDate, String name, String content) throws AdException{
		try{
			Document document = new Document(projectID, authorID, createDate, name, content);			
			begin();
			getSession().save(document);
			commit();			
			return document;
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not create document with name" + name, e);
		}
	}
	
	public void delete(Document document) throws AdException{
		try{
			begin();
			getSession().delete(document);
			commit();

		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
			throw new AdException("Could not delete document " + "{" + document.getDocumentID() + "}", e);
		}
	}
}
