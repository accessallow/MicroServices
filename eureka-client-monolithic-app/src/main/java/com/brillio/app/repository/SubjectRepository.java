package com.brillio.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brillio.app.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer>{
	
}
