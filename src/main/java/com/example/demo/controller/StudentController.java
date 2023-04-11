package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.AuthenticateService;



@Controller
public class StudentController {
	
	@Autowired
	StudentRepository studRepo;
	
	
	/*** Open Student Registration Page ***/
	@RequestMapping("/studentReg")
	String studentRegistrationPage(Model model) {
		
		Student student =new Student();
		model.addAttribute("student",student);
		
		return "studentRegistration";
		
	}
	
	/*** Add Student Data ***/
	@RequestMapping("/addStudentData")
	String addStudentData(@ModelAttribute("student") Student student) {
		
		studRepo.save(student);
		System.out.println("Data added to DataBase.");
		return "redirect:/allStudents";
	}
	
	/*** View Student Data ***/
	@RequestMapping("/allStudents")
	String showAllStudents(Model model){
		
		List<Student> list = studRepo.findAll();
		
		model.addAttribute("students",list);
		
		return "allStudents";
	}
	
	/*** Delete Student Data ***/
	@RequestMapping("/deleteStudentData/{id}")
	String deleteStudent(@PathVariable ("id") int id) {
		
		studRepo.deleteById(id);
		return "redirect:/allStudents";
	}
	
	/*** Update Student Data ***/
	@RequestMapping("/updateStudentData/{id}")
	String updateStudent(@PathVariable("id") int id, Model model) {
		
		Student student = studRepo.getReferenceById(id);
		
		model.addAttribute("students",student);
		
		return"updateStudentForm";
	}
	
	@RequestMapping("login-student")
	String openStudentLogin() {
		return "studentLoginPage";
	}
	
	
	@Autowired
	CourseRepository courseRepo ;
	@RequestMapping("studentdashboard")
	String openStudentDashboard(Model model) {
		
		List<Course> list = courseRepo.findByStatus("Published");
		
		model.addAttribute("allCoursesList",list);
		return "studentdashboard";
	}
	
	
	
	
	
	@Autowired
	AuthenticateService authserv;
	
	@RequestMapping("/loginStudent")
	String loginAuthenticateStudent(@RequestParam("email") String email,
			@RequestParam("password") String password,
			HttpSession session, Model model) {
		
		if(authserv.studentAuthenticate(email, password)) {
			session.setAttribute("sessionStudent", email);
			return "redirect:/studentdashboard";
		}
		else {
			System.out.println("Login Failed !");
			session.setAttribute("errMsg", "Invalid Credentials !!");
			return "redirect:/login-student";
		}
	}
	
	@RequestMapping("/logout-student")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login-teacher";
    }
	
}
