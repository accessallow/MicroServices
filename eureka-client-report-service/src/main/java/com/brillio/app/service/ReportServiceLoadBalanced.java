package com.brillio.app.service;

import com.brillio.app.model.Exam;
import com.brillio.app.model.Registration;
import com.brillio.app.model.Student;
import com.brillio.app.model.Subject;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class ReportServiceLoadBalanced {

	@Autowired
	RestTemplate restTemplate;
	
	
	@HystrixCommand(fallbackMethod = "studentFallback",
			commandProperties = {
					@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1000"),
					@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="3"),
					@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="5000"),
					@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
			} )
	public Student[] getStudentData(int rollNumber) {
		String urlStudent = "http://STUDENT-SERVICE/find_by_roll_number?roll_number=" + rollNumber;

		Student[] studentObjs = restTemplate.getForEntity(urlStudent, Student[].class).getBody();
		
		return studentObjs;
	}
	
	
	@HystrixCommand(fallbackMethod = "subjectFallback",
			commandProperties = {
					@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1000"),
					@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="3"),
					@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="5000"),
					@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
			})
	public Subject[] getSubjectData() {
		String urlSubjects = "http://SUBJECT-SERVICE/data";

		Subject[] subjectObjs = restTemplate.getForEntity(urlSubjects, Subject[].class).getBody();
		
		return subjectObjs;
	}
	
	@HystrixCommand(fallbackMethod = "registrationFallback",
			commandProperties = {
					@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1000"),
					@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="3"),
					@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="5000"),
					@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
			})
	public Registration[] getRegistrationData(int rollNumber) {
		String urlRegistration = "http://REGISTRATION-SERVICE/find_by_roll_number?roll_number=" + rollNumber;

		Registration[] registrationObjs = restTemplate.getForEntity(urlRegistration, Registration[].class).getBody();
		
		return registrationObjs;
	}
	
	@HystrixCommand(fallbackMethod = "examFallback",
			commandProperties = {
					@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1000"),
					@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="3"),
					@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="5000"),
					@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
			})
	public Exam[] getExamData(int rollNumber) {
		String urlExam = "http://EXAM-SERVICE/find_by_roll_number?roll_number=" + rollNumber;

		Exam[] examObjs = restTemplate.getForEntity(urlExam, Exam[].class).getBody();
		
		return examObjs;
	}

	public Student[] studentFallback(int rollNumber){
		return new Student[0];
	}
	public Subject[] subjectFallback(){
		return new Subject[0];
	}
	public Registration[] registrationFallback(int rollNumber){
		return new Registration[0];
	}
	public Exam[] examFallback(int rollNumber){
		return new Exam[0];
	}
}
