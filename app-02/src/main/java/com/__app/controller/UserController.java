package com.__app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.__app.Dto.QuoteResponseDTO;
import com.__app.Dto.ResetPwdDTO;
import com.__app.Dto.UserDTO;
import com.__app.Service.EmailService;
import com.__app.Service.UserServiceImpl;


@Controller
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping("/")
	public String login() {
		return "index";
	}
	@PostMapping("/userValidation")
	public String userValidation(@ModelAttribute UserDTO userDto,Model model) {
		String result="";
		UserDTO user=userService.login(userDto.getEmail(), userDto.getPwd());
		model.addAttribute("email",userDto.getEmail());
		if(user != null) {
			if(user.getPwdUpdated().equals("NO"))
				result="passwordReset";
			else {
				QuoteResponseDTO quotation = userService.getQuotation();
				model.addAttribute("quote", quotation);
				result="dashboard";
			}
		}else {
			model.addAttribute("emsg","User Credentials are Invalid..");
			result="index";
		}
		return result;
	}
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("countries", userService.getCountries());
		return "register";
	}
	@PostMapping("/register")
	public String registeredUser(@ModelAttribute UserDTO userDto,Model model) {

		String result="index";
		if(userService.isEmailUnique(userDto.getEmail())) {
			userService.register(userDto);
			model.addAttribute("success", "Successfully Registered, Login with Password.");
		}else {
			model.addAttribute("message","Email already Exist, Try another or Login..!");
		}
		
		return result;
	}
	@GetMapping("/states/{countryId}")
	@ResponseBody
	public Map<Integer, String> getStates(@PathVariable Integer countryId) {
		//System.out.println("entered states..."+userService.getStates(countryId));
		return userService.getStates(countryId);
	}

	@GetMapping("/cities/{stateId}")
	@ResponseBody
	public Map<Integer, String> getCities(@PathVariable Integer stateId) {
		//System.out.println("Entered Citites...."+stateId);
		return userService.getCities(stateId);
	}
	@PostMapping("/resetPwd")
	public String resetPwd(@ModelAttribute ResetPwdDTO resetPwd, Model model) {
		UserDTO login = userService.login(resetPwd.getEmail(), resetPwd.getOldPwd());
		if (login == null) {
			model.addAttribute("emsg", "Old Pwd is incorrect");
			return "resetPwd";
		}

		if (resetPwd.getNewPwd().equals(resetPwd.getConfirmPwd())) {
			userService.resetPwd(resetPwd);
			QuoteResponseDTO quotation = userService.getQuotation();
			model.addAttribute("quote", quotation);
			return "dashboard";
		} else {
			model.addAttribute("emsg", "New Pwd and Confirm Pwd Not Matching");
			return "resetPwd";
		}
	}
	@GetMapping("/getQuote")
	public String getQuote(Model model) {
		QuoteResponseDTO quotation = userService.getQuotation();
		model.addAttribute("quote", quotation);
		return "dashboard";
	}
}
