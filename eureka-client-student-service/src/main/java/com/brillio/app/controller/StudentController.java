package com.brillio.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.brillio.app.model.Student;
import com.brillio.app.repository.StudentRepository;


@CrossOrigin
@RestController
@RequestMapping("/")
public class StudentController {
	@Autowired
	StudentRepository studentRepo;
	
	@GetMapping("/data")
	public List<Student> getAll(){
		return studentRepo.findAll();
	}
	
	@PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE)
	public Student createStudent(@RequestBody Student requestStudent){
		Student s = new Student();
		s.setName(requestStudent.getName());
		studentRepo.save(s);
		return s;
	}
	
	@GetMapping("/find_by_roll_number")
	public List<Student> findByRollNumber(HttpServletRequest  request) {
		int rollNumber = Integer.parseInt(request.getParameter("roll_number"));
		return studentRepo.findByRollNumber(rollNumber);
	}

	@PostMapping(value = "/delete",consumes = MediaType.APPLICATION_JSON_VALUE)
	public Student deleteStudent(@RequestBody Student s){
		studentRepo.delete(s);
		return s;
	}

	@PostMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE)
	public Student updateStudent(@RequestBody Student s){
		Student sDb = studentRepo.findByRollNumber(s.getRollNumber()).get(0);
		sDb.setName(s.getName());
		studentRepo.save(sDb);
		return sDb;
	}
}
