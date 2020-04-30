package com.brillio.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "examination")
public class Exam {
	@Id
	@Column(name = "exam_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int registrationId;
	
	@Column(name = "roll_no")
	private int rollNumber;
	
	@Column(name = "subject_code") 
	private int subjectCode;
	
	@Column(name = "marks")
	private int marks;

	public int getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}

	public int getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}

	public int getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(int subjectCode) {
		this.subjectCode = subjectCode;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "Exam [registrationId=" + registrationId + ", rollNumber=" + rollNumber + ", subjectCode=" + subjectCode
				+ ", marks=" + marks + "]";
	}

	
}
