package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Dto.Dashboard_Represent;
import com.example.demo.Dto.ViewEnqueriesFilterReq;
import com.example.demo.Entity.Enqueries;
import com.example.demo.Service.CounselloerService;
import com.example.demo.Service.EnqueryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class Enqueries_Controller {
	
	@Autowired
	private EnqueryService enqServ;
	
	@Autowired
	private CounselloerService counServ;
	
	@GetMapping("/enquery") //send empty enquire object to controller to UI
	
	public String addEnquerypage(Model model) {
		
		Enqueries enq = new Enqueries();
		model.addAttribute("enquerie", enq);
		return "enqueryForm";
	}
	
	@GetMapping("/edit/{id}")
	public String editEnquery(@PathVariable int id, Model model) {
		
	    Enqueries enqu = enqServ.getEnqbyId(id);
	    
	    System.out.println("enqe details "+enqu);
	    
	    model.addAttribute("enquerie", enqu);
	    return "Edit";
	} 
	
	@GetMapping("/dashboard")//Display dashboard  page
	public String displayDashboard(HttpServletRequest request, Model model) {
		          
	 HttpSession session = request.getSession(false);
				 
	Integer counsellorId = (Integer) session.getAttribute("counsellorId");
	Dashboard_Represent dobj = counServ.getDashboardinfo(counsellorId);
	
	model.addAttribute("dashboardInfo", dobj);
	
	return "dashboard";
	}
	
	@GetMapping("/View-Enqueries") // Display the View Enquery page
	public String getEnqueries(HttpServletRequest request,Model model) {
		
        //get existing session
        HttpSession session = request.getSession(false);	 
       Integer counsellorId = (Integer) session.getAttribute("counsellorId");
       
       List<Enqueries> enqlist  = enqServ.getAllEnq(counsellorId);
          
       model.addAttribute("enq",  enqlist);
      
       ViewEnqueriesFilterReq filter = new ViewEnqueriesFilterReq();
      model.addAttribute("viewenqueryfilter", filter);
		
		return "viewEnqueries";
		
	}
	
	@PostMapping("/filter-enqeries")
	public String filterEnq(ViewEnqueriesFilterReq viewenqueryfilter, HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");
		
		List<Enqueries> enqList = enqServ.getEnqueriesWithfilter(viewenqueryfilter, counsellorId);
		System.out.println("enq list "+enqList);
		
		model.addAttribute("enq",enqList);
		
		model.addAttribute("viewenqueryfilter", viewenqueryfilter); // Ensure filter is passed back
		
		return "viewEnqueries";
		
	} 
	
	@PostMapping("/addEnq")
	
	public String handleAddEnqueriy(@ModelAttribute("enquerie") Enqueries enq, HttpServletRequest req, Model model) throws Exception {
		//get existing session object
		
		HttpSession session = req.getSession(false);
		
		//System.out.println("session  apg :"+session);
		 
	  Integer counsellorId = (Integer) session.getAttribute("counsellorId");
	  
	//  System.out.println("Counselor id "+counsellorId);
	  
	  boolean isSaved = enqServ.addEnquerie(enq, counsellorId);
	  
	  if(isSaved) {
		  
		  model.addAttribute("smsg", "Enquerie Added Sucessfully");
		  
	  }else {
		  model.addAttribute("fmsg", "Failed to add Enquerie!!!");  
	  }
		return "enqueryForm";
	}
} 
