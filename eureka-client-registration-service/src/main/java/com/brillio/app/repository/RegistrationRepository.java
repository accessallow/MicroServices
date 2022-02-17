package com.brillio.app.repository;

import java.util.List;

import com.brillio.app.model.RegistrationResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import com.brillio.app.model.Registration;
import org.springframework.data.jpa.repository.Query;

public interface RegistrationRepository extends JpaRepository<Registration, Integer>{
	public List<Registration> findByRollNumber(int rollNumber);

	@Query(
			value = "select r.*, st.name as studentName, s.subject_name as subjectName from registration r, student st, subject s where r.roll_number=st.roll_no and r.subject_code=s.subject_code",
			nativeQuery = true
	)
	List<RegistrationResponse> findAllRegistrations();
}
