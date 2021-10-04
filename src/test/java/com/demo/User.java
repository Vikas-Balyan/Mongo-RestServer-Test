package com.demo;


import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;



public class User {
	
	String _id;
	private String  firstName;
	private String  surName;
	private Date dob;
	private String  title;


	public User(String _id, String firstName, String surName, Date dob, String title) {
		super();
		this._id = _id;
		this.firstName = firstName;
		this.surName = surName;
		this.dob = dob;
		this.title = title;
	}
	
	public User( String firstName, String surName, Date dob, String title) {
		super();
		this.firstName = firstName;
		this.surName = surName;
		this.dob = dob;
		this.title = title;
	}



	public User() {
		super();
		// TODO Auto-generated constructor stub
	}






	public String get_id() {
		return _id;
	}







	public void set_id(String _id) {
		this._id = _id;
	}







	public String getFirstName() {
		return firstName;
	}







	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}







	public String getSurName() {
		return surName;
	}







	public void setSurName(String surName) {
		this.surName = surName;
	}








	public Date getDob() {
		return dob;
	}







	public void setDob(Date dob) {
		this.dob = dob;
	}







	public String getTitle() {
		return title;
	}







	public void setTitle(String title) {
		this.title = title;
	}





	

}
