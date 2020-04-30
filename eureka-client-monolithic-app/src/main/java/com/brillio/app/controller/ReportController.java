package com.brillio.app.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/report")
public class ReportController {
	

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/find_by_roll_number")
	public Map<String,Object[]> getAll(HttpServletRequest request) {

		int rollNumber = Integer.parseInt(request.getParameter("roll_number"));
		Map<String,Object[]> responseMap = new HashMap<String, Object[]>();

		// Get student details
		String urlStudent = "http://STUDENT-SERVICE/student/find_by_roll_number?roll_number=" + rollNumber;

		Object[] studentObjs = restTemplate.getForEntity(urlStudent, Object[].class).getBody();
		

		for (Object o : studentObjs) {
			System.out.println(o);
		}

		// Get student details
		String urlSubjects = "http://STUDENT-SERVICE/subject/data";

		Object[] subjectObjs = restTemplate.getForEntity(urlSubjects, Object[].class).getBody();

		for (Object o : subjectObjs) {
			System.out.println(o);
		}

		// Get subject-registration details
		String urlRegistration = "http://STUDENT-SERVICE/registration/find_by_roll_number?roll_number=" + rollNumber;

		Object[] registrationObjs = restTemplate.getForEntity(urlRegistration, Object[].class).getBody();

		for (Object o : registrationObjs) {
			System.out.println(o);
		}

		// Get exam details
		String urlExam = "http://STUDENT-SERVICE/exam/find_by_roll_number?roll_number=" + rollNumber;

		Object[] examObjs = restTemplate.getForEntity(urlExam, Object[].class).getBody();

		for (Object o : examObjs) {
			System.out.println(o);
		}
		
		
		
		responseMap.put("student",studentObjs);
		responseMap.put("subjects",subjectObjs);
		responseMap.put("registration",registrationObjs);
		responseMap.put("exams",examObjs);
		
		return responseMap;
	}

}
