package com.__app.controller;

import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.__app.DTO.CounsellorDto;
import com.__app.DTO.DashboardResponseDto;
import com.__app.entity.Counsellor;
import com.__app.service.CounsellorService;
import com.__app.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class CounsellorController {
	
	@Autowired
	private CounsellorService counsellorService;
	
	@Autowired
	private EnquiryService enquiryService;
	
	@GetMapping("/createCounsellor")
	public String createCounsellor() {
		return "createAcc";
	}
	
	
	@PostMapping("/addCounsellor")
	public String addCounsellor(@ModelAttribute CounsellorDto counsellorDto, Model model) {
		
	    if (counsellorService.uniqueEmailCheck(counsellorDto.getEmail())) {
	        if (counsellorService.register(counsellorDto)) {
	        	model.addAttribute("success", "Account created successfully! Please log in with your credentials.");
	            return "index"; 
	        } else {
	            model.addAttribute("error", "Registration failed. Please try again.");
	        }
	    } else {
	        model.addAttribute("error", "Email already exists. Please use a different email.");
	    }
	    return "createAcc"; // Redirect back to account creation page with an error message
	}

	
	@PostMapping("/userValidation")
	public String getUserValidation(HttpServletRequest req,@RequestParam String userName, @RequestParam String password, Model model) {
		
	    Counsellor counsellor = counsellorService.login(userName, password);
	    String result="";

	    if (counsellor == null) {
	        model.addAttribute("error", "Invalid username or password. Please try again.");
	        result= "index"; 
	    }else {
	    	DashboardResponseDto dashboardInfo = enquiryService.getDashboardInfo(counsellor.getCounsellorId());
	 	   
	    	HttpSession session=req.getSession(true);
	    	session.setAttribute("counsellorId",counsellor.getCounsellorId());
	    	
	 	   model.addAttribute("dashboardInfo",dashboardInfo);
	 	  // model.addAttribute("counsellorId",counsellor.getCounsellorId());
	 	    result= "CounsellorDashboard"; 
	    }
	   return result;
	}
	@GetMapping("/logout")
	public String logout(HttpServletRequest req, Model model) {

		HttpSession session = req.getSession(false);
	    if (session != null) {
	        session.invalidate();
	    }

	    model.addAttribute("message", "You have successfully logged out.");
	    return "redirect:/";
	}

}
