package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Dto.Dashboard_Represent;
import com.example.demo.Entity.Counsellors;
import com.example.demo.Service.CounselloerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class Counsellors_Controller {
	
	@Autowired
	private CounselloerService counServ;
	
	 @GetMapping("/")  //load empty form  
	 
	    public String index(Model model) {
	    	Counsellors coObj = new Counsellors();
	    	    	//sending data from controller to UI
	    	
	    	model.addAttribute("counsellor", coObj);
	    	
	        return "index"; // This will return "index.html" from templates
	    }
	    
	    @PostMapping("/login")
	    public String login(Counsellors counsellor, HttpServletRequest request, Model model2) {
	    	
	    	Counsellors c= counServ.login(counsellor.getEmail(), counsellor.getPass());
	    	
	    	System.out.println(c);
	    	
	    	if(c == null) {
	    		
	    		model2.addAttribute("emsg", "Invalid Credintials");
	    		model2.addAttribute("counsellor", new Counsellors());
	    		return "index";
	    		
	    	} else {
	    		
	    		 //retrieve the id for future operation
	    		 HttpSession session = request.getSession(true);
	    		   
	    		 session.setAttribute("counsellorId", c.getId());
	    		   
	    		Dashboard_Represent dobj = counServ.getDashboardinfo(c.getId());
	    		
	    		model2.addAttribute("dashboardInfo", dobj);
	    	 
	    	
			return "dashboard";	
	       }
	   }
	    
	    @GetMapping("/register")
	    public String register(Model model) {
	    	
	      Counsellors coObj = new Counsellors();
	    	
	    	//sending data from controller to UI
	    	model.addAttribute("counsellor", coObj);
	    	
	        return "register";	
	    }
	    
	    @PostMapping("/register")
	    public String handleRegister(Counsellors counsellor, RedirectAttributes redirectAttributes) {
	        Counsellors byEmail = counServ.findbyEmail(counsellor.getEmail());

	        if (byEmail != null) {
	            redirectAttributes.addFlashAttribute("rgfail", "Duplicate Email");
	            return "redirect:/register";
	        }

	        boolean register = counServ.register(counsellor);

	        if (register) {
	            redirectAttributes.addFlashAttribute("rgsucess", "Registration Success!!!");
	        } else {
	            redirectAttributes.addFlashAttribute("rgfail", "Registration Failed");
	        }

	        return "redirect:/register";
	    }
	    
	    @GetMapping("/logout")
	    public String logout(HttpServletRequest req) {
	    	
	    	//Get Existing session and invalidate it
	    	HttpSession session = req.getSession();
	    	session.invalidate();
	    	
	    	//redirect to login page
	    	return "redirect:/";
	    	
	    }

}
