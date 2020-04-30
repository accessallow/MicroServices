package com.brillio.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brillio.app.model.Exam;

public interface ExamRepository extends JpaRepository<Exam, Integer>{
	public List<Exam> findByRollNumber(int rollNumber);
}
