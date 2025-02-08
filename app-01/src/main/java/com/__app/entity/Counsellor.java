package com.__app.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Counsellor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer counsellorId;
	private String name;
	private String email;
	private String pwd;
	private String phno;

	@OneToMany(mappedBy = "counsellor", cascade = CascadeType.ALL)
	private List<Enquiry> enquiries;
	
	public Counsellor() {
		
	}
	
	public Counsellor(String name, String email, String pwd, String phno, List<Enquiry> enquiries) {
		super();
		this.name = name;
		this.email = email;
		this.pwd = pwd;
		this.phno = phno;
		this.enquiries = enquiries;
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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public List<Enquiry> getEnquiries() {
		return enquiries;
	}

	public void setEnquiries(List<Enquiry> enquiries) {
		this.enquiries = enquiries;
	}

	public Integer getCounsellorId() {
		return counsellorId;
	}
}
