package com.brillio.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class RegistrationResponse extends Registration{
    @Column(name = "studentName")
    private String studentName;
    @Column(name = "subjectName")
    private String subjectName;

    public RegistrationResponse(String studentName, String subjectName) {
        this.studentName = studentName;
        this.subjectName = subjectName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
