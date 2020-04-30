package com.brillio.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brillio.app.model.Registration;
import com.brillio.app.model.Student;
import com.brillio.app.repository.StudentRepository;



@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	StudentRepository studentRepo;
	
	@GetMapping("/data")
	public List<Student> getAll(){
		return studentRepo.findAll();
	}
	
	@PostMapping("/create")
	public Student createStudent(HttpServletRequest  request){
		Student s = new Student();
		s.setName(request.getParameter("student_name"));
		studentRepo.save(s);
		return s;
	}
	
	@GetMapping("/find_by_roll_number")
	public List<Student> findByRollNumber(HttpServletRequest  request) {
		int rollNumber = Integer.parseInt(request.getParameter("roll_number"));
		return studentRepo.findByRollNumber(rollNumber);
	}
}
