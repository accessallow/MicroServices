package com.brillio.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brillio.app.model.Registration;
import com.brillio.app.repository.RegistrationRepository;


@RestController
@RequestMapping("/registration")
public class RegistrationController {
	@Autowired
	RegistrationRepository registrationRepo;
	
	@GetMapping("/data")
	public List<Registration> getAll(){
		return registrationRepo.findAll();
	}
	
	@PostMapping("/create")
	public Registration createRegistration(HttpServletRequest  request){
		Registration s = new Registration();
		s.setRollNumber(Integer.parseInt(request.getParameter("roll_number")));
		s.setSubjectCode(Integer.parseInt(request.getParameter("subject_code")));
		registrationRepo.save(s);
		return s;
	}
	
	@GetMapping("/find_by_roll_number")
	public List<Registration> findByRollNumber(HttpServletRequest  request) {
		int rollNumber = Integer.parseInt(request.getParameter("roll_number"));
		return registrationRepo.findByRollNumber(rollNumber);
	}
}
