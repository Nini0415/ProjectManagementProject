package com.pmbox.pm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="todocomment_table")
public class TodoComment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="commentID", unique=true, nullable=false)
	private Integer commentID;
	
	@Column(name="todoID")
	private Integer todoID;
	
	@Column(name="commenterID")
	private Integer commenterID;
	
	@Column(name="content")
	private String content;
	
	@Column(name="commentDate")
	private String commentDate;
	
	public TodoComment() {
	}
	public TodoComment(Integer todoID, Integer commenterID, 
			String commentDate, String content) {
		this.todoID = todoID;
		this.commenterID = commenterID;
		this.commentDate = commentDate;
		this.content = content;
	}
	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public int getTodoID() {
		return todoID;
	}
	public void setTodoID(int todoID) {
		this.todoID = todoID;
	}
	public int getCommenterID() {
		return commenterID;
	}
	public void setCommenterID(int commenterID) {
		this.commenterID = commenterID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	public String toString(){
		return "todocommentID:"+ commentID + "; " + content;
	}
}
