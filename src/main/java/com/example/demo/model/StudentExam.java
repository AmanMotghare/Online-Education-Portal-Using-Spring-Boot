package com.example.demo.model;

public class StudentExam {
	
	private int id;
	private String que;
	private String selectedAns;
	private String studentEmail;
	
	public StudentExam() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public StudentExam(int id, String que, String selectedAns, String studentEmail) {
		super();
		this.id = id;
		this.que = que;
		this.selectedAns = selectedAns;
		this.studentEmail = studentEmail;
	}
	
	public StudentExam(String que, String selectedAns, String studentEmail) {
		super();
		this.que = que;
		this.selectedAns = selectedAns;
		this.studentEmail = studentEmail;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQue() {
		return que;
	}
	public void setQue(String que) {
		this.que = que;
	}
	public String getSelectedAns() {
		return selectedAns;
	}
	public void setSelectedAns(String selectedAns) {
		this.selectedAns = selectedAns;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	@Override
	public String toString() {
		return "StudentExam [id=" + id + ", que=" + que + ", selectedAns=" + selectedAns + ", studentEmail="
				+ studentEmail + "]";
	}
	
	
	
	

}
