package com.__app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Enquiry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enqId;//why we take Integer Object instead of taking int or Long primitive type val
	private String stuName;
	private String stuPhno;
	private String classMode;
	private String course;
	private String enqStatus;

	@ManyToOne
	@JoinColumn(name = "counsellor_id",referencedColumnName = "counsellorId")
	private Counsellor counsellor;

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuPhno() {
		return stuPhno;
	}

	public void setStuPhno(String stuPhno) {
		this.stuPhno = stuPhno;
	}

	public String getClassMode() {
		return classMode;
	}

	public void setClassMode(String classMode) {
		this.classMode = classMode;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getEnqStatus() {
		return enqStatus;
	}

	public void setEnqStatus(String enqStatus) {
		this.enqStatus = enqStatus;
	}

	public Counsellor getCounsellor() {
		return counsellor;
	}

	public void setCounsellor(Counsellor counsellor) {
		this.counsellor = counsellor;
	}

	public Integer getEnqId() {
		return enqId;
	}
}
