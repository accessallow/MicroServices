package com.brillio.app.repository;

import java.util.List;

import com.brillio.app.model.RegistrationResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import com.brillio.app.model.Registration;
import org.springframework.data.jpa.repository.Query;

public interface RegistrationRepository extends JpaRepository<Registration, Integer>{
	public List<Registration> findByRollNumber(int rollNumber);
}
