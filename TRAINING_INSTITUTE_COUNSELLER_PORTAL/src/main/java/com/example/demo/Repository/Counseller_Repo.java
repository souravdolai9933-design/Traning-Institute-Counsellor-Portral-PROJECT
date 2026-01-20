package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Counsellors;

public interface Counseller_Repo extends JpaRepository< Counsellors, Integer> {
	
	//select * from table where email=:email
	public Counsellors findByEmail(String email);     
	
	//select * from counsellorsDetails where email=:email and pass=:password
	 public Counsellors findByEmailAndPass(String email, String pass);  // 'pass' should match entity field name
	 
} 