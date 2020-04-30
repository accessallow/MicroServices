package com.brillio.app.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
		
		
		responseMap.put("student",reportService.getStudentData(rollNumber));
		responseMap.put("subjects",reportService.getSubjectData());
		responseMap.put("registration",reportService.getRegistrationData(rollNumber));
		responseMap.put("exams",reportService.getExamData(rollNumber));
		
		return responseMap;
	}
	

}
