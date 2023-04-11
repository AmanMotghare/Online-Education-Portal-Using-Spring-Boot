package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true)
	private String courseTitle;
	@Column
	private String authorEmail;
	@Column
	private String amount;
	@Column
	private String date;
	@Column
	private String status="Pending";
	
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Course(String courseTitle, String authorEmail, String amount, String date, String status) {
		super();
		this.courseTitle = courseTitle;
		this.authorEmail = authorEmail;
		this.amount = amount;
		this.date = date;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public String getAuthorEmail() {
		return authorEmail;
	}
	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", courseTitle=" + courseTitle + ", authorEmail=" + authorEmail + ", amount="
				+ amount + ", date=" + date + ", status=" + status + "]";
	}

	


	
	

}
