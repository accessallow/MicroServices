package com.brillio.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
	
//	@PostMapping("/create")
//	public Subject createSubject(HttpServletRequest  request){
//		Subject s = new Subject();
//		s.setName(request.getParameter("subject_name"));
//		subjectRepo.save(s);
//		return s;
//	}

	@PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE)
	public Subject createSubject(@RequestBody Subject requestSubject){
		Subject s = new Subject();
		s.setName(requestSubject.getName());
		subjectRepo.save(s);
		return s;
	}

	@PostMapping(value = "/delete",consumes = MediaType.APPLICATION_JSON_VALUE)
	public Subject deleteSubject(@RequestBody Subject s){
		subjectRepo.delete(s);
		return s;
	}

	@PostMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE)
	public Subject updateSubject(@RequestBody Subject s){
		Subject sDb = subjectRepo.findById(s.getCode()).get();
		sDb.setName(s.getName());
		subjectRepo.save(sDb);
		return sDb;
	}
}
