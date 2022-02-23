package com.brillio.app.service;

import com.brillio.app.model.Registration;
import com.brillio.app.model.RegistrationResponse;
import com.brillio.app.model.Student;
import com.brillio.app.model.Subject;
import com.brillio.app.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class RegistrationService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RegistrationRepository registrationRepository;

    public List<RegistrationResponse> getDetailedRegistrationData(){
        List<Registration> registrations = this.registrationRepository.findAll();
        Student[] students = restTemplate.getForEntity("http://STUDENT-SERVICE/data", Student[].class).getBody();
        Subject[] subjects = restTemplate.getForEntity("http://SUBJECT-SERVICE/data", Subject[].class).getBody();

        Map<Integer,String> studentNameMap = new HashMap<>();
        Map<Integer,String> subjectNameMap = new HashMap<>();

        Arrays.stream(students).forEach(s -> studentNameMap.put(s.getRollNumber(),s.getName()));
        Arrays.stream(subjects).forEach(s -> subjectNameMap.put(s.getCode(),s.getName()));

        List<RegistrationResponse> response = new ArrayList<>();
        registrations.forEach(r -> {
            RegistrationResponse rep = new RegistrationResponse(r);
            rep.setSubjectName(subjectNameMap.getOrDefault(r.getSubjectCode(),"--"));
            rep.setStudentName(studentNameMap.getOrDefault(r.getRollNumber(),"--"));
            response.add(rep);
        });
        return response;
    }
}
