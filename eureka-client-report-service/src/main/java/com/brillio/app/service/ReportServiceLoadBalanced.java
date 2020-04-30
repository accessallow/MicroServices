package com.brillio.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class ReportServiceLoadBalanced {

	@Autowired
	RestTemplate restTemplate;
	
	
	@HystrixCommand(fallbackMethod = "myFallbackMethod",
			commandProperties = {
					@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1000"),
					@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="3"),
					@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="5000"),
					@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
			} )
	public Object[] getStudentData(int rollNumber) {
		String urlStudent = "http://STUDENT-SERVICE/find_by_roll_number?roll_number=" + rollNumber;

		Object[] studentObjs = restTemplate.getForEntity(urlStudent, Object[].class).getBody();
		
		return studentObjs;
	}
	
	
	@HystrixCommand(fallbackMethod = "myFallbackMethod",
			commandProperties = {
					@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1000"),
					@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="3"),
					@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="5000"),
					@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
			})
	public Object[] getSubjectData() {
		String urlSubjects = "http://SUBJECT-SERVICE/data";

		Object[] subjectObjs = restTemplate.getForEntity(urlSubjects, Object[].class).getBody();
		
		return subjectObjs;
	}
	
	@HystrixCommand(fallbackMethod = "myFallbackMethod",
			commandProperties = {
					@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1000"),
					@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="3"),
					@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="5000"),
					@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
			})
	public Object[] getRegistrationData(int rollNumber) {
		String urlRegistration = "http://REGISTRATION-SERVICE/find_by_roll_number?roll_number=" + rollNumber;

		Object[] registrationObjs = restTemplate.getForEntity(urlRegistration, Object[].class).getBody();
		
		return registrationObjs;
	}
	
	@HystrixCommand(fallbackMethod = "myFallbackMethod",
			commandProperties = {
					@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1000"),
					@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="3"),
					@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="5000"),
					@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
			})
	public Object[] getExamData(int rollNumber) {
		String urlExam = "http://EXAM-SERVICE/find_by_roll_number?roll_number=" + rollNumber;

		Object[] examObjs = restTemplate.getForEntity(urlExam, Object[].class).getBody();
		
		return examObjs;
	}
	
	
	private static final Object[] EMPTY_ARRAY = {};
	
	public Object[] myFallbackMethod(int rollNumber) {
		return EMPTY_ARRAY;
	}
	
	
	public Object[] myFallbackMethod() {
		return EMPTY_ARRAY;
	}
}
