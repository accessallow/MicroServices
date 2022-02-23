package com.brillio.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.brillio.app.model.RegistrationResponse;
import com.brillio.app.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.brillio.app.model.Registration;
import com.brillio.app.repository.RegistrationRepository;


@RestController
@RequestMapping("/")
public class RegistrationController {
	@Autowired
	RegistrationRepository registrationRepo;

	@Autowired
	RegistrationService registrationService;

	@GetMapping("/data")
	public List<RegistrationResponse> getAll(){
		return registrationService.getDetailedRegistrationData();
	}
	
	@GetMapping("/find_by_roll_number")
	public List<Registration> findByRollNumber(HttpServletRequest  request) {
		int rollNumber = Integer.parseInt(request.getParameter("roll_number"));
		return registrationRepo.findByRollNumber(rollNumber);
	}

	@PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE)
	public Registration createRegistration(@RequestBody Registration r){
		Registration s = new Registration();
		s.setRollNumber(r.getRollNumber());
		s.setSubjectCode(r.getSubjectCode());
		registrationRepo.save(s);
		return s;
	}

	@PostMapping(value = "/delete",consumes = MediaType.APPLICATION_JSON_VALUE)
	public Registration deleteRegistration(@RequestBody Registration s){
		registrationRepo.delete(s);
		return s;
	}

	@PostMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE)
	public Registration updateStudent(@RequestBody Registration s){
		Registration sDb = registrationRepo.findById(s.getRegistrationId()).get();
		sDb.setSubjectCode(s.getSubjectCode());
		sDb.setRollNumber(s.getRollNumber());
		registrationRepo.save(sDb);
		return sDb;
	}
}
