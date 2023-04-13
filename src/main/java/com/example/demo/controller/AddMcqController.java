package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.example.demo.model.McqCreate;
import com.example.demo.model.McqTest;
import com.example.demo.model.Student;
import com.example.demo.repository.McqCreateRepo;
import com.example.demo.repository.McqTestRepo;


@Controller
public class AddMcqController {
	
	@Autowired
	McqTestRepo mcqtrepo;
	
	@RequestMapping("/mcq/{id}")
	String mcq(Model model,@PathVariable(name = "id") int qid) {
		
	//	McqTestDao db=new McqTestDao();
		McqTest q =mcqtrepo.getReferenceById(qid);
		
		Student s=new Student();
		model.addAttribute("stud", s);
		model.addAttribute("q", q);
		
		return "page-mcq";
	}
	
	@RequestMapping("/test1/{email}")
	String myfun1(@PathVariable("email") String email, Model model) {
		McqCreate mc = new McqCreate();
		mc.setEmail(email);
		model.addAttribute("mcqCobj", mc);
		mc.show();
		return "mcqcreate";
	}
	//////////////////////////////////////////////////////////////

	
	@Autowired
	McqCreateRepo mcqcredb;
	
	@RequestMapping("/test2")
	String myfun2(@ModelAttribute("mcqCobj") McqCreate mc, RedirectAttributes rattrs) {
		System.out.println("myfun2 Test 2");
		mc.show();
	//	McqCreateDao db = new McqCreateDao();
		mcqcredb.save(mc);
		rattrs.addAttribute("mcqCobj", mc.getEmail() + "-" + mc.getTitel());
		return "redirect:/test4";
	}

	@RequestMapping("/test4")
	String myfun4(@ModelAttribute("mcqCobj") String mc, Model model) {
		McqTest mt = new McqTest();
		System.out.println("Data in Test4 MC : " + mc);

		String emailTitel[] = mc.split("-");
		mt.setEmail(emailTitel[0]);
		mt.setTitle(emailTitel[1]);

		mt.show();
		model.addAttribute("mcqobj", mt);
		mt.show();
		System.out.println("Data to build Test");
		return "buildtest";
	}

	@RequestMapping("/AddQue")
	String myfun5(@ModelAttribute("mcqobj") McqTest mt, RedirectAttributes rattrs) {

		mt.show();
	//	McqTestDao db = new McqTestDao();
		mcqtrepo.save(mt);

		rattrs.addAttribute("mcqCobj", mt.getEmail() + "-" + mt.getTitle());

		return "redirect:/test4";
	}

	// preview
	@RequestMapping("/preview")
	public String viewHomePage(Model model) {
	//	McqTestDao db = new McqTestDao();

		List<McqTest> questionlist = mcqtrepo.findAll();
		model.addAttribute("questionlist", questionlist);

		return "testpreview";
	}

	/*
	 * @RequestMapping("/edit/{id}") public ModelAndView
	 * showEditProductPage(@PathVariable(name = "id") int id) { ModelAndView mav =
	 * new ModelAndView("editquestion"); // McqTestDao db = new McqTestDao();
	 * McqTest question = mcqtrepo.getReferenceById(id); mav.addObject("que",
	 * question);
	 * 
	 * return mav; }
	 */
	
	
	@RequestMapping("/editMcq/{id}")
	public String editpage(@PathVariable(name = "id") int id,Model model) {
		/*
		 * McqTest test = // McqTestDao db = new McqTestDao();
		 */		McqTest question = mcqtrepo.getReferenceById(id);
		model.addAttribute("ques", question);

		return "editquestion";
	}

	@RequestMapping("/UpdateQue")
	String update(@ModelAttribute("ques") McqTest mt)
	{
		System.out.println("Done 1");
		mcqtrepo.save(mt);
		return "redirect:/preview";
	}
	
	
	/*String updatque(@ModelAttribute("que") McqTest mt, RedirectAttributes rattrs) {

		System.out.println("Done 1");
		mt.show();
		//McqTestDao db = new McqTestDao();
		mcqtrepo.save(mt);

		rattrs.addAttribute("mcqCobj", mt.getEmail() + "-" + mt.getTitle());

		return "redirect:/preview";
	}*/

	@RequestMapping("/deleteMcq/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
	//	McqTestDao db = new McqTestDao();
		mcqtrepo.deleteById(id);
		return "redirect:/preview";
	}

	@RequestMapping("/done")
	String done() {
		return "done";
	}
	
	
}
