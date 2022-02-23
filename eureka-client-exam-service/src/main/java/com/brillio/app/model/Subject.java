package com.brillio.app.model;

import javax.persistence.*;


public class Subject {
	@Id
	@Column(name = "subject_code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int code;
	
	@Column(name = "subject_name")
	private String name;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
