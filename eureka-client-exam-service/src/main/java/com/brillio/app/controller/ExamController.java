package com.brillio.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.brillio.app.model.ExamResponse;
import com.brillio.app.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.brillio.app.model.Exam;
import com.brillio.app.repository.ExamRepository;


@RestController
@RequestMapping("/")
public class ExamController {
	@Autowired
	ExamRepository examRepo;

	@Autowired
	ExamService examService;
	
	@GetMapping("/data")
	public List<Exam> getAll(){
		return examRepo.findAll();
	}

	@GetMapping("/detailed_data")
	public List<ExamResponse> getDetailedData(){
		return examService.getDetailedRegistrationData();
	}

	@PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE)
	public Exam create(@RequestBody Exam r){
		Exam s = new Exam();
		s.setRegistrationId(r.getRegistrationId());
		s.setMarks(r.getMarks());
		examRepo.save(s);
		return s;
	}

	@PostMapping(value = "/delete",consumes = MediaType.APPLICATION_JSON_VALUE)
	public Exam delete(@RequestBody Exam s){
		examRepo.delete(s);
		return s;
	}

	@PostMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE)
	public Exam update(@RequestBody Exam s){
		Exam sDb = examRepo.findById(s.getExamId()).get();
		sDb.setRegistrationId(s.getRegistrationId());
		sDb.setMarks(s.getMarks());
		examRepo.save(sDb);
		return sDb;
	}
}
