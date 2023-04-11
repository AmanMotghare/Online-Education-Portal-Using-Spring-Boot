package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student_tbl")
public class Student {
	
	@Id 
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	@Column(unique=true)
    private String email;
	@Column
	private String password;
	
	
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Student(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}



	public Student(int id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public static boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	
	
	
}