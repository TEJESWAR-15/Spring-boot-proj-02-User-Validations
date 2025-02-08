package com.__app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.__app.entity.Enquiry;

@Repository
public interface EnquiryRepo extends JpaRepository<Enquiry,Integer> {

	List<Enquiry> getByCounsellorCounsellorId(Integer counsellorId);

	//List<Enquiry> findAllById(Integer counsellorId);
	
	
	//@Query("select count(e) from Enquiry e where e.counsellorId= :counsellorId")
	//Long getTotalCount(Integer counsellorId);
	
	

}
