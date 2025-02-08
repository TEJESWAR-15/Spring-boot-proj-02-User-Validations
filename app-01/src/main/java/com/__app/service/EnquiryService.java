package com.__app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.__app.DTO.DashboardResponseDto;
import com.__app.DTO.EnqFilterDto;
import com.__app.DTO.EnquiryDto;
import com.__app.Repository.CounsellorRepo;
import com.__app.Repository.EnquiryRepo;
import com.__app.entity.Counsellor;
import com.__app.entity.Enquiry;

@Service
public class EnquiryService {
	
	@Autowired
	private EnquiryRepo enquiryRepo;
	
	@Autowired
	private CounsellorRepo counsellorRepo;
	
	public DashboardResponseDto getDashboardInfo(Integer csId) {
		
		List<Enquiry>  enquiries = enquiryRepo.findAll();
		
		List<Enquiry> result= enquiries.stream().filter(x->x.getCounsellor().getCounsellorId()==csId).collect(Collectors.toList());

		 Long totalEnqCnt = (long) result.size();
		 Long openEnqCnt=(long) 0;
		 Long enrolledEnqCnt=(long) 0;
		 Long lostEnqCnt=(long) 0;
		for(Enquiry e : result) {
			
			if(e.getEnqStatus().equals("Open"))
				openEnqCnt++;
			else if(e.getEnqStatus().equals("Lost"))
				lostEnqCnt++;
			else if(e.getEnqStatus().equals("Enrolled"))
				enrolledEnqCnt++;
		}
		
		DashboardResponseDto dashboardDto = new DashboardResponseDto(totalEnqCnt,openEnqCnt,enrolledEnqCnt,lostEnqCnt);
		return dashboardDto;
	}

	public boolean addEnquiry(EnquiryDto enqDTO, Integer counsellorId) {
		Enquiry enq= new Enquiry();
		enq.setStuName(enqDTO.getStuName());
		enq.setStuPhno(enqDTO.getStuPhno());
		enq.setClassMode(enqDTO.getClassMode());
		enq.setCourse(enqDTO.getCourse());
		enq.setEnqStatus(enqDTO.getEnqStatus());
		enq.setCounsellor(counsellorRepo.getReferenceById(counsellorId));
		Enquiry e=enquiryRepo.save(enq);
		return e != null;
	}

	public List<Enquiry> getEnquiries(Integer counsellorId) {
		/*
		 * List<Enquiry> result=
		 * enquiryRepo.findAll().stream().filter(x->x.getCounsellor()
		 * .getCounsellorId()==counsellorId) .collect(Collectors.toList());
		 */
		
		List<Enquiry> result= enquiryRepo.getByCounsellorCounsellorId(counsellorId);
		
		return result;
	}

	public List<EnquiryDto> getEnquiries(EnqFilterDto filterDTO, Integer counsellorId){
		List<Enquiry> result=  enquiryRepo.findAll().stream().filter(x->x.getCounsellor()
				.getCounsellorId()==counsellorId)
				.collect(Collectors.toList());
		List<EnquiryDto> enquiryDtos=new ArrayList<>();
		for(Enquiry e : result) {
			
			if(filterDTO.getClassMode().equals(e.getClassMode()) && filterDTO.getCourse().equals(e.getCourse()) && filterDTO.getEnqStatus().equals(e.getEnqStatus())) {
				 String stuName =e.getStuName();
				 String stuPhno = e.getStuPhno();
				 String classMode = e.getClassMode();
				 String course = e.getCourse();
				 String enqStatus = e.getEnqStatus();
				 EnquiryDto enqDto = new EnquiryDto(stuName,stuPhno,classMode,course,enqStatus);
				 enquiryDtos.add(enqDto);
			}
			 
		}
		return enquiryDtos;
		
	}
	public boolean updateRecord(EnquiryDto enqDto,Integer enqId) {
		
		Enquiry enq= enquiryRepo.getReferenceById(enqId);
		enq.setStuName(enqDto.getStuName());
		enq.setStuPhno(enqDto.getStuPhno());
		enq.setClassMode(enqDto.getClassMode());
		enq.setCourse(enqDto.getCourse());
		enq.setEnqStatus(enqDto.getEnqStatus());
		
		Enquiry enquiry =enquiryRepo.saveAndFlush(enq);
		return enquiry != null;
	}

	public EnquiryDto getEnquiryById(Integer enqId) {
		Optional<Enquiry> e =enquiryRepo.findById(enqId);
		EnquiryDto result = new EnquiryDto(e.get().getStuName(),e.get().getStuPhno(),e.get().getClassMode(),e.get().getCourse(),e.get().getEnqStatus());
		return result;
		
	}
}
