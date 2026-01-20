package com.example.demo.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.Dashboard_Represent;
import com.example.demo.Entity.Counsellors;
import com.example.demo.Entity.Enqueries;
import com.example.demo.Repository.Counseller_Repo;
import com.example.demo.Repository.Enqueries_Repo;

@Service
public class CounselloerServiceImpl implements CounselloerService {
	
   @Autowired
   private Counseller_Repo counRepo;
   
   @Autowired
   private Enqueries_Repo enqRepo;
  
   
	@Override
	public boolean register(Counsellors counsellor) {
		
		  Counsellors savecounsellor =  counRepo.save(counsellor);
		  System.out.println("save counsellors "+savecounsellor);
		  
		  if (savecounsellor != null && savecounsellor.getId() != null) {
			  System.out.println("return true sucess ");
			    return true;
			}
		   
		return false;
	}

	@Override
	public Counsellors login(String email, String pass) {
	    return counRepo.findByEmailAndPass(email, pass); // ✅ Updated method name
	}


	@Override
	public Dashboard_Represent getDashboardinfo(Integer counsellorId) {
		
		Dashboard_Represent response = new Dashboard_Represent();
		
	   List<Enqueries> enqlist =enqRepo.getEnqueriesByCounsellorId(counsellorId);
	   
	   int totalEnq = enqlist.size();
	   
	   System.out.println("total enq :"+totalEnq);
	   
	   int enrolledEnqs = enqlist.stream()
			   .filter(e->e.getEnqueriestatus().equals("Enrolled"))
			   .collect(Collectors.toList())
			   .size();
	   
	   int lostEnqs = enqlist.stream()
			   .filter(e->e.getEnqueriestatus().equals("Lost"))
			   .collect(Collectors.toList())
			   .size();
	   
	   int openEnqs = enqlist.stream()
			   .filter(e->e.getEnqueriestatus().equals("Open"))
			   .collect(Collectors.toList())
			   .size();
	   
	   response.setTotalEnq(totalEnq);
	   response.setEnrollEnq(enrolledEnqs);
	   response.setOpenEnq(openEnqs);
	   response.setLostEnq(lostEnqs);
	   
		return response;
	}
	
	@Override
	public Counsellors findbyEmail(String email) {
		
		return counRepo.findByEmail(email);
	}
}
