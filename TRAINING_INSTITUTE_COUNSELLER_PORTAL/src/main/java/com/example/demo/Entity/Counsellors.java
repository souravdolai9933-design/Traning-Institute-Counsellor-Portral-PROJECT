package com.example.demo.Entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="Counsellors_Details")
@Entity
public class Counsellors {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Email",unique = true)
	private String email;
	
	@Column(name="Password")
	private String pass;
	
	@Column(name="Phone_No")
	private double Phno;
	
	@CreationTimestamp
	private LocalDate createDate;
	
	@UpdateTimestamp
	private LocalDate updatedDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public double getPhno() {
		return Phno;
	}
	public void setPhno(double phno) {
		Phno = phno;
	}
	public LocalDate getCreateDate() {
		return createDate;
	 }
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	public LocalDate getUpdatedDate() {
		return updatedDate;
	}
	
	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	@Override
	public String toString() {
		return "Counsellors [id=" + id + ", name=" + name + ", email=" + email + ", pass=" + pass + ", Phno=" + Phno
				+ ", createDate=" + createDate + ", updatedDate=" + updatedDate + "]";
	}
	
}
