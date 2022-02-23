package com.brillio.app.model;


public class RegistrationResponse extends Registration{
    private String studentName;
    private String subjectName;

    public RegistrationResponse(String studentName, String subjectName) {
        this.studentName = studentName;
        this.subjectName = subjectName;
    }

    public RegistrationResponse(Registration r){
        this.setRegistrationId(r.getRegistrationId());
        this.setRollNumber(r.getRollNumber());
        this.setSubjectCode(r.getSubjectCode());
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
