package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.Enqueries;

public interface Enqueries_Repo extends JpaRepository<Enqueries, Integer> {
	
	@Query(value="SELECT * FROM enqaueries_details WHERE counseller_id = :counsellor_id", nativeQuery = true)
	public List<Enqueries> getEnqueriesByCounsellorId(Integer counsellor_id);	//This method fetch End from counselors id
	
}
