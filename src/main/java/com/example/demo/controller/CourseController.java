package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Course;
import com.example.demo.model.CourseTopic;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.CourseTopicRepository;

@Controller
public class CourseController {
	
	
	//Add Course Details
	
		@RequestMapping("/addCourse")	
		String addCourse(Model  model) {
			
			Course course = new Course();
			model.addAttribute("course",course);
			return "addCourse";
		}
		
		
		@Autowired
		CourseRepository courseRepo;
		@RequestMapping("/addCourseData")
		String addCourseData(@ModelAttribute("course") Course course ) {
			
			courseRepo.save(course);
			System.out.println("Course Added.");
			
			return "redirect:/allCoursesTeacher";
		}
		
		
		@RequestMapping("/allCoursesTeacher")	
		String allCourses(Model model) {
			
			List<Course> list = courseRepo.findAll();
			
			model.addAttribute("allCoursesList",list);
			
			return "allCoursesTeacher";
		}
		
		
		
		@RequestMapping("/allCoursesHomepage")	
		String allCoursesHomepage(Model model, HttpSession session) {
			
			if(session.getAttribute("sessionStudent") != null) {
			
			List<Course> list = courseRepo.findByStatus("Published");
			
			model.addAttribute("allCoursesListHomepage",list);
			
			return "coursesHomepage";
			}
			else
			{
				return "redirect:/login-student";
			}
		}
		

		@RequestMapping("/addTopic/{title}")
		String addCourseTopicPage(@PathVariable("title") String title, Model model) {
			
			System.out.println("Course Name : "+ title);
			Course course = courseRepo.findBycourseTitle(title);
			//System.out.println(course);
			model.addAttribute("courseKey", course);
			
			//Creating Empty Object
			CourseTopic topic = new CourseTopic();
			model.addAttribute("topicKey", topic);
			System.out.println("Topic,s empty object created.");
			
			return "addCourseTopic";
		}

		
		@Autowired
		CourseTopicRepository topicRepo;
		
		@RequestMapping("/setTopic")
		String setCourseTopicPage(@ModelAttribute("topicKey") CourseTopic topic) {
			
			
			String url=topic.getYoutubeLink();
			String urlId[]=url.split("=");
			
			topic.setYoutubeLink(urlId[1]);
			
			topicRepo.save(topic);
			System.out.println("Topics added.");
		
			return "redirect:allCoursesTeacher";
		}
		
		
		
		@RequestMapping("/courseSingle/{courseTitle}")
		String openSingleCourse(@PathVariable("courseTitle") String courseTitle, Model model ) {
			
			List<CourseTopic> list = topicRepo.findBycourseTitle(courseTitle);
			model.addAttribute("courseTopics",list);
			
			for (CourseTopic courseTopic : list) {
				
				model.addAttribute("coursename",courseTopic.getCourseTitle());
				model.addAttribute("authoremail",courseTopic.getAuthorEmail());
				
				System.out.println(courseTopic);
			}
			
			return "courseSingle";
		}
		
		
	
}
