package com.brillio.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brillio.app.model.Subject;
import com.brillio.app.repository.SubjectRepository;


@RestController
@RequestMapping("/")
public class SubjectController {
	@Autowired
	SubjectRepository subjectRepo;
	
	@GetMapping("/data")
	public List<Subject> getAll(){
		return subjectRepo.findAll();
	}
	
	@PostMapping("/create")
	public Subject createSubject(HttpServletRequest  request){
		Subject s = new Subject();
		s.setName(request.getParameter("subject_name"));
		subjectRepo.save(s);
		return s;
	}
}
