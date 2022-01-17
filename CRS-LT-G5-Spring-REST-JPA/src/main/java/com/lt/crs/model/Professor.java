package com.lt.crs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Abdul,Sai kumar,Rohan,Siva,Nikhil
 *
 */
@Entity
@Table
public class Professor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int professorId;
	private String professorName;
	private String professorDepartment;


	public int getProfessorId() {
		return professorId;
	}

	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}

	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	

	public String getProfessorDepartment() {
		return professorDepartment;
	}

	public void setProfessorDepartment(String professorDepartment) {
		this.professorDepartment = professorDepartment;
	}

	
}
