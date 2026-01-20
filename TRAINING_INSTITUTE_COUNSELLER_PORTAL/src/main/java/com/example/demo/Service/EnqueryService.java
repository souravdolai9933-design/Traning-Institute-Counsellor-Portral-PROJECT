package com.example.demo.Service;

import java.util.List;

import com.example.demo.Dto.ViewEnqueriesFilterReq;
import com.example.demo.Entity.Enqueries;

public interface EnqueryService {
	
	public boolean addEnquerie(Enqueries enq,Integer counsellerid ) throws Exception; //Add enquire Details 
	
	public List<Enqueries> getEnqueriesWithfilter(ViewEnqueriesFilterReq filterreq, Integer counsellerid ); // For filter enquire purpose
	
	public Enqueries getEnqbyId(Integer enqid); // For Edit enquire purpose

	List<Enqueries> getAllEnq(Integer counsollerid); //Display all Enquire data
}
