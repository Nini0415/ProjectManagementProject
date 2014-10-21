package com.pmbox.pm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="userfile_table")
public class UserFile {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="fileID", unique=true, nullable=false)
	private int fileID;
	
	@Column(name="projectID")
	private int projectID;
	
	@Column(name="uploaderID")
	private int uploaderID;

	@Column(name="name")
	private String name;
	
	@Column(name="uploadTime")
	private String uploadTime;
	
	public UserFile() {
	}	
	
	
	public UserFile(int projectID, int uploaderID, String name,
			String uploadTime) {
		super();
		this.projectID = projectID;
		this.uploaderID = uploaderID;
		this.name = name;
		this.uploadTime = uploadTime;
	}


	public int getFileID() {
		return fileID;
	}

	public void setFileID(int fileID) {
		this.fileID = fileID;
	}

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public int getUploaderID() {
		return uploaderID;
	}

	public void setUploaderID(int uploaderID) {
		this.uploaderID = uploaderID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}	
}
