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
	private int examId;

	@Column(name = "registration_id")
	private int registrationId;

	@Column(name = "marks")
	private int marks;


	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public int getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}
}
