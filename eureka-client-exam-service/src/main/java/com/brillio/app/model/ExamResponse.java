package com.brillio.app.model;

public class ExamResponse extends Exam{
    private Student student;
    private Registration registration;
    private Subject subject;

    public ExamResponse(Exam e) {
        this.setExamId(e.getExamId());
        this.setRegistrationId(e.getRegistrationId());
        this.setMarks(e.getMarks());
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
