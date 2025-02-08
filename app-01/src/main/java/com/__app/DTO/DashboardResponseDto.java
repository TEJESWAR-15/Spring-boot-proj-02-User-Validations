package com.__app.DTO;

public class DashboardResponseDto {
	private Long totalEnqCnt;
	private Long openEnqCnt;
	private Long enrolledEnqCnt;
	private Long lostEnqCnt;
	
	
	public DashboardResponseDto() {
		
	}
	
	public DashboardResponseDto(Long totalEnqCnt, Long openEnqCnt, Long enrolledEnqCnt, Long lostEnqCnt) {
		super();
		this.totalEnqCnt = totalEnqCnt;
		this.openEnqCnt = openEnqCnt;
		this.enrolledEnqCnt = enrolledEnqCnt;
		this.lostEnqCnt = lostEnqCnt;
	}
	
	public Long getTotalEnqCnt() {
		return totalEnqCnt;
	}
	public void setTotalEnqCnt(Long totalEnqCnt) {
		this.totalEnqCnt = totalEnqCnt;
	}
	public Long getOpenEnqCnt() {
		return openEnqCnt;
	}
	public void setOpenEnqCnt(Long openEnqCnt) {
		this.openEnqCnt = openEnqCnt;
	}
	public Long getEnrolledEnqCnt() {
		return enrolledEnqCnt;
	}
	public void setEnrolledEnqCnt(Long enrolledEnqCnt) {
		this.enrolledEnqCnt = enrolledEnqCnt;
	}
	public Long getLostEnqCnt() {
		return lostEnqCnt;
	}
	public void setLostEnqCnt(Long lostEnqCnt) {
		this.lostEnqCnt = lostEnqCnt;
	}
	
	
}
