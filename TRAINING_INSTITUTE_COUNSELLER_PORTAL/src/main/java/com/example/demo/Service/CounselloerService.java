package com.example.demo.Service;

import com.example.demo.Dto.Dashboard_Represent;
import com.example.demo.Entity.Counsellors;

public interface CounselloerService {
	
	public Counsellors findbyEmail(String email);// prevent duplicate Counselor register
	
	public boolean register(Counsellors counsellor);// For counselor register purpose
	
	public Counsellors login(String email,String Password);// Counselor login purpose
	
	public Dashboard_Represent getDashboardinfo(Integer counsellerid);// Display counselor dashboard data
	
}
