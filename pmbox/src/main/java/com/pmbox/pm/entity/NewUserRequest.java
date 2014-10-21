package com.pmbox.pm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="newuserrequest_table")
public class NewUserRequest {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="requestID", unique=true, nullable=false)
	private int requestID;
	
	@Column(name="senderID")
	private int senderID;
	@Column(name="adminID")
	private int adminID;
	@Column(name="requestUserName")
	private String requestUserName;
	@Column(name="sendDate")
	private String sendDate;
	@Column(name="finishDate")
	private String finishDate;
	@Column(name="initialPass")
	private String initialPass;
	
	public NewUserRequest() {
	}
	
	public NewUserRequest(int senderID, String requestUserName, String sendDate){
		this.senderID = senderID;
		this.requestUserName = requestUserName;
		this.sendDate = sendDate;
		this.adminID = 0;
	}

	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public int getSenderID() {
		return senderID;
	}

	public void setSenderID(int senderID) {
		this.senderID = senderID;
	}

	public int getAdminID() {
		return adminID;
	}

	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}

	public String getRequestUserName() {
		return requestUserName;
	}

	public void setRequestUserName(String requestUserName) {
		this.requestUserName = requestUserName;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public String getInitialPass() {
		return initialPass;
	}

	public void setInitialPass(String initialPass) {
		this.initialPass = initialPass;
	}
	
	public String toString(){
		return "Request user account for:" + requestUserName;
	}
}
