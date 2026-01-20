package com.example.demo.Entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name="Enqaueries_Details")
@Entity
public class Enqueries {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="Student_name")
	private String stuname;
	@Column(name="Student_Phn_no")
	private Long stuPhoneno;
	@Column(name="Studednt_course_name")
	private String stucoursename;
	@Column(name="Class_Mode")
	private String classmode;
	@Column(name="Enqueries_Status")
	private String Enqueriestatus;
	
	
	@Column(name="Created_Date")
	@CreationTimestamp
	private LocalDate createdDate;
	
	@Column(name="Updated_Date")
	@UpdateTimestamp
	private LocalDate updatedDate;
	
	@ManyToOne
	@JoinColumn(name="counseller_id")
	private Counsellors counseller;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStuname() {
		return stuname;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}

	public Long getStuPhoneno() {
		return stuPhoneno;
	}

	public void setStuPhoneno(Long stuPhoneno) {
		this.stuPhoneno = stuPhoneno;
	}

	public String getStucoursename() {
		return stucoursename;
	}

	public void setStucoursename(String stucoursename) {
		this.stucoursename = stucoursename;
	}

	public String getClassmode() {
		return classmode;
	}

	public void setClassmode(String classmode) {
		this.classmode = classmode;
	}

	public String getEnqueriestatus() {
		return Enqueriestatus;
	}

	public void setEnqueriestatus(String enqueriestatus) {
		Enqueriestatus = enqueriestatus;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Counsellors getCounseller() {
		return counseller;
	}

	public void setCounseller(Counsellors counseller) {
		this.counseller = counseller;
	}

	@Override
	public String toString() {
		
		return "Enqueries [id=" + id + ", stuname=" + stuname + ", stuPhoneno=" + stuPhoneno + ", stucoursename="
				+ stucoursename + ", classmode=" + classmode + ", Enqueriestatus=" + Enqueriestatus + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + ", counseller=" + counseller + "]";
	}
	
}
