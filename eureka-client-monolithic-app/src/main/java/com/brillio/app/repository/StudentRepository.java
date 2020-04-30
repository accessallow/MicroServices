package com.brillio.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brillio.app.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	public List<Student> findByRollNumber(int rollNumber);
}
