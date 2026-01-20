package com.example.demo.Service;

import java.util.List;
import io.micrometer.common.util.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.ViewEnqueriesFilterReq;
import com.example.demo.Entity.Counsellors;
import com.example.demo.Entity.Enqueries;
import com.example.demo.Repository.Counseller_Repo;
import com.example.demo.Repository.Enqueries_Repo;

@Service
public class EnqueryServiceImpl implements EnqueryService {
	
	@Autowired
	private Counseller_Repo counRepo;
	
	@Autowired
	private Enqueries_Repo enqRepo;

	@Override
	public boolean addEnquerie(Enqueries enq, Integer counsellerid) throws Exception {
		
		 Counsellors counsellors = counRepo.findById(counsellerid).orElse(null);
		 
		 if(counsellors == null) {
			 
			 throw new Exception("No counsellors found");
		 }
		 //Associated between Counselors and enquires
		 
		 enq.setCounseller(counsellors);
		 
		    Enqueries save = enqRepo.save(enq);

		    if(save.getId() !=null) {
		    	return true;
		    }
		 
		return false;
		
	}

	@Override
	public List<Enqueries> getAllEnq(Integer counsollerid) {
		
		return enqRepo.getEnqueriesByCounsellorId(counsollerid);
	}

	@Override
	public List<Enqueries> getEnqueriesWithfilter(ViewEnqueriesFilterReq filterreq, Integer counsellerid) {
		
	//QBE implementation (Dynamic Query prepare)
		Enqueries enq = new Enqueries();
		
		if(StringUtils.isNotEmpty(filterreq.getClassmode())) {
			enq.setClassmode(filterreq.getClassmode());
		}
		if(StringUtils.isNotEmpty(filterreq.getCoursename())) {
			enq.setStucoursename(filterreq.getCoursename());
		}
		if(StringUtils.isNotEmpty(filterreq.getCourseStatus())) {
			enq.setEnqueriestatus(filterreq.getCourseStatus());
			
		}
		Counsellors c = counRepo.findById(counsellerid).orElse(null);
		enq.setCounseller(c);
		
		Example<Enqueries> of = Example.of(enq); // based on condition it retrieve the records
		
		List<Enqueries> enqList = enqRepo.findAll(of);
		
		return enqList;
	}
	
	@Override
	public Enqueries getEnqbyId(Integer enqid) {
		
	    return enqRepo.findById(enqid).orElse(null); // or throw an exception
	}


}
