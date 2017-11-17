package com.nostrasoft.da.rest.model;

import com.nostrasoft.da.rest.util.JsonUtils;

public class Commit 
{
	private static final String EMPTY = "";
	
	private String id = EMPTY;
	private String message= EMPTY;
	private String date= EMPTY;
	private String type= EMPTY;
	private String commiterName= EMPTY;
	private String commiterEmail= EMPTY;
	private String files= EMPTY;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = getEmptyIfNull(id);
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = getEmptyIfNull(message);
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = getEmptyIfNull(date);
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = getEmptyIfNull(type);
	}
	public String getCommiterName() {
		return commiterName;
	}
	public void setCommiterName(String commiterName) {
		this.commiterName = getEmptyIfNull(commiterName);
	}
	public String getCommiterEmail() {
		return commiterEmail;
	}
	public void setCommiterEmail(String commiterEmail) {
		this.commiterEmail = getEmptyIfNull(commiterEmail);
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = getEmptyIfNull(files);
	}
	
	private String getEmptyIfNull(String value)
	{
		if (null == value)
		{
			return EMPTY;
		}
		
		return value;
	}
	@Override
	public String toString() {
		return JsonUtils.toJSON(this);
	}
}
