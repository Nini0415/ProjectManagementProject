package com.pmbox.pm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="document_table")
public class Document {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="documentID", unique=true, nullable=false)
	private int documentID;
	
	@Column(name="projectID")
	private int projectID;
	
	@Column(name="authorID")
	private int authorID;

	@Column(name="name")
	private String name;
	
	@Column(name="content")
	private String content;
	
	@Column(name="createDate")
	private String createDate;
	
	public Document() {
	}
	
	public Document(int projectID, int authorID, String createDate, String name, String content) {
		this.projectID = projectID;
		this.authorID = authorID;
		this.createDate = createDate;
		this.name = name;
		this.content = content;
	}

	public Integer getDocumentID() {
		return documentID;
	}

	public void setDocumentID(Integer documentID) {
		this.documentID = documentID;
	}

	public Integer getProjectID() {
		return projectID;
	}

	public void setProjectID(Integer projectID) {
		this.projectID = projectID;
	}

	public Integer getAuthorID() {
		return authorID;
	}

	public void setAuthorID(Integer authorID) {
		this.authorID = authorID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
