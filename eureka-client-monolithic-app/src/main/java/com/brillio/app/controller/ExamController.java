package com.brillio.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brillio.app.model.Exam;
import com.brillio.app.repository.ExamRepository;


@RestController
@RequestMapping("/exam")
public class ExamController {
	@Autowired
	ExamRepository examRepo;
	
	@GetMapping("/data")
	public List<Exam> getAll(){
		return examRepo.findAll();
	}
	
	@PostMapping("/create")
	public Exam createRegistration(HttpServletRequest  request){
		Exam s = new Exam();
		s.setRollNumber(Integer.parseInt(request.getParameter("roll_number")));
		s.setSubjectCode(Integer.parseInt(request.getParameter("subject_code")));
		s.setMarks(Integer.parseInt(request.getParameter("marks")));
		
		System.out.println(s);
		
		examRepo.save(s);
		return s;
	}
	
	@GetMapping("/find_by_roll_number")
	public List<Exam> findByRollNumber(HttpServletRequest  request) {
		int rollNumber = Integer.parseInt(request.getParameter("roll_number"));
		return examRepo.findByRollNumber(rollNumber);
	}
}
