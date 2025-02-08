package com.__app.DTO;

public class EnquiryDto{
	private String stuName;
	private String stuPhno;
	private String classMode;
	private String course;
	private String enqStatus;
	
	public EnquiryDto(){
		
	}
	
	public EnquiryDto(String stuName, String stuPhno, String classMode, String course, String enqStatus) {
		super();
		this.stuName = stuName;
		this.stuPhno = stuPhno;
		this.classMode = classMode;
		this.course = course;
		this.enqStatus = enqStatus;
	}
	
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
	
}
