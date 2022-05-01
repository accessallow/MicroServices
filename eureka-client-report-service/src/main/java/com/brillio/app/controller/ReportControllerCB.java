package com.brillio.app.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.brillio.app.model.Registration;
import com.brillio.app.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brillio.app.service.ReportServiceLoadBalanced;

@RestController
@Component
@RequestMapping("/report_cb")
public class ReportControllerCB {
	
	@Autowired
	ReportServiceLoadBalanced reportService;

	@GetMapping("/find_by_roll_number")
	public Map<String,Object[]> getAll(HttpServletRequest request) {

		int rollNumber = Integer.parseInt(request.getParameter("roll_number"));
		Map<String,Object[]> responseMap = new HashMap<String, Object[]>();

		Registration[] registrations = reportService.getRegistrationData(rollNumber);
		Set<Integer> studentRegisteredSubjects = new HashSet<>();
		Arrays.asList(registrations).forEach(r -> {
			studentRegisteredSubjects.add(r.getSubjectCode());
		});
		List<Subject> studentSubjects = new ArrayList<>();
		Arrays.asList(reportService.getSubjectData()).forEach(s -> {
			if(studentRegisteredSubjects.contains(s.getCode())){
				studentSubjects.add(s);
			}
		});
		responseMap.put("student",reportService.getStudentData(rollNumber));
		responseMap.put("subjects",studentSubjects.toArray());
		responseMap.put("registration",registrations);
		responseMap.put("exams",reportService.getExamData(rollNumber));
		
		return responseMap;
	}
	

}
