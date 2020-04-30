package com.brillio.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brillio.app.model.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Integer>{
	public List<Registration> findByRollNumber(int rollNumber);
}
