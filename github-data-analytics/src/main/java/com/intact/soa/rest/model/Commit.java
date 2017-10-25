package com.intact.soa.rest.model;

public class Commit 
{
	private String id;
	private String message;
	private String date;
	private String type;
	private String commiterName;
	private String commiterEmail;
	private String files;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCommiterName() {
		return commiterName;
	}
	public void setCommiterName(String commiterName) {
		this.commiterName = commiterName;
	}
	public String getCommiterEmail() {
		return commiterEmail;
	}
	public void setCommiterEmail(String commiterEmail) {
		this.commiterEmail = commiterEmail;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	@Override
	public String toString() {
		return "Commit [id=" + id + ", message=" + message + ", date=" + date + ", type=" + type + ", commiterName="
				+ commiterName + ", commiterEmail=" + commiterEmail + ", files=" + files + "]";
	}
	
	
}
