package com.example.demo.controller;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Admin;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.AuthenticateService;

@Controller
public class AdminController {
	
	@Autowired
	AdminRepository adminService;
	
	@RequestMapping("/reg")
	public String registerPage(Model model) {
		
		Admin admin = new Admin();
		model.addAttribute("AdminKey", admin);
		
		System.out.println("Admin's Khali object Created.");
		return "page-register";
	}
	
	@RequestMapping("/register-admin")
	public String saveData(@ModelAttribute ("AdminKey") Admin admin ,HttpSession session) {
		
		try {
			adminService.save(admin);
			
		} catch (DataIntegrityViolationException e) {
			
			
			session.setAttribute("sessionKey", "Email exists.");
			
			System.out.println("Email already registered.");
			return "redirect:/reg";
		}
		 
		System.out.println("Admin data saved successfully.");
		
		return "redirect:/login";
		
	}
	
	@RequestMapping("/login")
	public String OpenloginPage() {
		return "page-login";
	}
	
	@Autowired
	AuthenticateService authServ;
	@RequestMapping("/logn")
	public String login(@RequestParam("email") String email,
						@RequestParam("password") String password,
						HttpSession session, Model model) {
		
		if(authServ.authenticate(email, password)) {
			session.setAttribute("sessionEmail", email);
			return "redirect:/";
		}else {
			System.out.println("Login Failed !");

			session.setAttribute("errMsg", "Invalid Credentials !!");
			return "redirect:/login";
		}
	}
	
	@RequestMapping("/logout-admin")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}