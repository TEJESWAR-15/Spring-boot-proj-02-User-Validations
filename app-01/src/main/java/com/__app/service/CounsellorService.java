package com.__app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.__app.DTO.CounsellorDto;
import com.__app.Repository.CounsellorRepo;
import com.__app.Repository.EnquiryRepo;
import com.__app.entity.Counsellor;
import com.__app.entity.Enquiry;

@Service
public class CounsellorService {
	
	@Autowired
	private EnquiryRepo enquiryRepo;
	
	@Autowired
	private CounsellorRepo counsellorRepo;
	
	public Counsellor login(String userName,String password) {
		
		Counsellor counsellor=(Counsellor) counsellorRepo.findByEmail(userName);
		if(counsellor == null)
			return null;
		else if(counsellor.getEmail().equals(userName) && (counsellor.getPwd().equals(password)))
		return counsellor;
		
		return null;
		
	}

	public boolean uniqueEmailCheck(String email) {
		Counsellor counsellor=counsellorRepo.findByEmail(email);
		//System.out.println(email+"entered into service layer..."+counsellor.getEmail());
		return counsellor == null;
	}
	

	public boolean register(CounsellorDto counsellorDto) {
		Counsellor counsellor=new Counsellor();
		counsellor.setName(counsellorDto.getName());
		counsellor.setEmail(counsellorDto.getEmail());
		counsellor.setPhno(counsellorDto.getPhno());
		counsellor.setPwd(counsellorDto.getPwd());
		counsellorRepo.save(counsellor);
		return counsellor != null;
	}
}
