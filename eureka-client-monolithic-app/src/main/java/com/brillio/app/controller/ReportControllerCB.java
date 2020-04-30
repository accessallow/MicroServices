package com.brillio.app.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping("/report_cb")
public class ReportControllerCB {
	

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/find_by_roll_number")
	public Map<String,Object[]> getAll(HttpServletRequest request) {

		int rollNumber = Integer.parseInt(request.getParameter("roll_number"));
		Map<String,Object[]> responseMap = new HashMap<String, Object[]>();
		
		
		responseMap.put("student",getStudentData(rollNumber));
		responseMap.put("subjects",getSubjectData());
		responseMap.put("registration",getRegistrationData(rollNumber));
		responseMap.put("exams",getExamData(rollNumber));
		
		return responseMap;
	}
	
	
	@HystrixCommand(fallbackMethod = "myFallbackMethod",
			commandProperties = {
					@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1000"),
					@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="3"),
					@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="1000"),
					@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
			} )
	public Object[] getStudentData(int rollNumber) {
		String urlStudent = "http://STUDENT-SERVICE/student/find_by_roll_number?roll_number=" + rollNumber;

		Object[] studentObjs = restTemplate.getForEntity(urlStudent, Object[].class).getBody();
		
		return studentObjs;
	}
	
	
	@HystrixCommand(fallbackMethod = "myFallbackMethod")
	public Object[] getSubjectData() {
		String urlSubjects = "http://STUDENT-SERVICE/subject/data";

		Object[] subjectObjs = restTemplate.getForEntity(urlSubjects, Object[].class).getBody();
		
		return subjectObjs;
	}
	
	@HystrixCommand(fallbackMethod = "myFallbackMethod")
	public Object[] getRegistrationData(int rollNumber) {
		String urlRegistration = "http://STUDENT-SERVICE/registration/find_by_roll_number?roll_number=" + rollNumber;

		Object[] registrationObjs = restTemplate.getForEntity(urlRegistration, Object[].class).getBody();
		
		return registrationObjs;
	}
	
	@HystrixCommand(fallbackMethod = "myFallbackMethod")
	public Object[] getExamData(int rollNumber) {
		String urlExam = "http://STUDENT-SERVICE/exam/find_by_roll_number?roll_number=" + rollNumber;

		Object[] examObjs = restTemplate.getForEntity(urlExam, Object[].class).getBody();
		
		return examObjs;
	}
	
	
	private static final Object[] EMPTY_ARRAY = {};
	
	public Object[] myFallbackMethod(int rollNumber) {
		return EMPTY_ARRAY;
	}

}
