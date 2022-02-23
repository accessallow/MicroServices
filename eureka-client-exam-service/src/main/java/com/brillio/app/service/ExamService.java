package com.brillio.app.service;

import com.brillio.app.model.*;
import com.brillio.app.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ExamService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ExamRepository examRepository;

    public List<ExamResponse> getDetailedRegistrationData(){
        List<Exam> exams = this.examRepository.findAll();
        Student[] students = restTemplate.getForEntity("http://STUDENT-SERVICE/data", Student[].class).getBody();
        Subject[] subjects = restTemplate.getForEntity("http://SUBJECT-SERVICE/data", Subject[].class).getBody();
        Registration[] registrations = restTemplate.getForEntity("http://REGISTRATION-SERVICE/data", Registration[].class).getBody();

        Map<Integer,Student> studentObjMap = new HashMap<>();
        Map<Integer,Subject> subjectObjMap = new HashMap<>();
        Map<Integer,Registration> regObjMap = new HashMap<>();

        Arrays.stream(students).forEach(s -> studentObjMap.put(s.getRollNumber(),s));
        Arrays.stream(subjects).forEach(s -> subjectObjMap.put(s.getCode(),s));
        Arrays.stream(registrations).forEach(s -> regObjMap.put(s.getRegistrationId(),s));

        List<ExamResponse> response = new ArrayList<>();
        exams.forEach(e -> {
            ExamResponse rep = new ExamResponse(e);
            Registration regObj = regObjMap.getOrDefault(e.getRegistrationId(),null);
            if(regObj!=null){
                rep.setRegistration(regObj);
                rep.setSubject(subjectObjMap.getOrDefault(regObj.getSubjectCode(),null));
                rep.setStudent(studentObjMap.getOrDefault(regObj.getRollNumber(),null));
                response.add(rep);
            }
        });
        return response;
    }
}
