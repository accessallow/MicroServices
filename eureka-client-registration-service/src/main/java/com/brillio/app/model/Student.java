package com.brillio.app.model;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
	@Id
	@Column(name = "roll_no")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int rollNumber;
	
	@Column(name = "name")
	private String name;

	public int getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
