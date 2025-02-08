package com.__app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.__app.DTO.DashboardResponseDto;
import com.__app.DTO.EnqFilterDto;
import com.__app.DTO.EnquiryDto;
import com.__app.entity.Enquiry;
import com.__app.service.EnquiryService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	
	@Autowired
	private EnquiryService service1;
	
	@GetMapping("/")
	public String getInfo() {
		service1.getDashboardInfo(1);
		return "index";
	}

	@GetMapping("/addEnquiry")
	public String addEnquiry(Model model) {
		return "addEnquiry";
	}
	@PostMapping("/addEnquiry")
	public String registerEnquiry(HttpServletRequest req,@ModelAttribute EnquiryDto enquiryDto,Model model) {
		
		HttpSession  session = req.getSession(false);
		
		String result="";
		if(session == null) {
			model.addAttribute("error","Session expired! Please Login Again ..");
			result="redirect:/";
		}else {
			Integer counsellorId=(Integer) session.getAttribute("counsellorId");
			
			if(session != null) {
				boolean b=service1.addEnquiry(enquiryDto, counsellorId);
				if(b==false)
					result= "/addEnquiry";
				else
					result="redirect:/viewEnquiries";
			}
		}
		return result;
	}
	@GetMapping("/viewEnquiries")
	public String viewEnquiries(HttpServletRequest servlet,Model model) {
		
		HttpSession session = servlet.getSession(false);
		String result="";
		if(session == null) {
			model.addAttribute("error","Session expired! Please Login Again ..");
			result="redirect:/";
		}else {
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");
		
		List<Enquiry> enqDto=service1.getEnquiries(counsellorId);
		model.addAttribute("enqDto",enqDto);
		model.addAttribute("counsellorId",counsellorId);
		result="/viewEnquiries";
		}
		return result;
	}
	@GetMapping("/editEnquiry")
	public String editEnquiry(@RequestParam Integer enqId,Model model) {
		EnquiryDto enquiryDto=service1.getEnquiryById(enqId);
		model.addAttribute("enquiryDto",enquiryDto);
		model.addAttribute("enqId",enqId);
		return "/editEnquiry";
	}
	@PostMapping("/updateEnquiry/{enqId}")
	public String updateEnquiry(@ModelAttribute EnquiryDto enqDto,@PathVariable Integer enqId,Model model) {
		
		service1.updateRecord(enqDto, enqId);
		
		return "redirect:/viewEnquiries?counsellorId="+1;
			
	}
	@GetMapping("/filterEnq")
	public String filterData(@ModelAttribute EnqFilterDto enqFilterDto,@RequestParam Integer counsellorId,Model model) {
		
		List<Enquiry> enqDto=service1.getEnquiries(counsellorId);
		List<Enquiry> result=new ArrayList<>();
		for(Enquiry enq : enqDto) {
			if((enq.getClassMode().equals(enqFilterDto.getClassMode()) || enqFilterDto.getClassMode().equals("Class Mode")) &&
					(enq.getCourse().equals(enqFilterDto.getCourse()) || enqFilterDto.getCourse().equals("Courses")) &&
					(enq.getEnqStatus().equals(enqFilterDto.getEnqStatus()) || enqFilterDto.getEnqStatus().equals("Status"))) {
				result.add(enq);
			}
				
		}
		model.addAttribute("enqDto",result);
		model.addAttribute("counsellorId",counsellorId);
		return "/viewEnquiries";
	}

}
