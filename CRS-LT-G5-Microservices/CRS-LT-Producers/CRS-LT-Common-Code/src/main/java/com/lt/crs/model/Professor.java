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
	/**
	 * @return the professorId
	 */
	public int getProfessorId() {
		return professorId;
	}
	/**
	 * @param professorId the professorId to set
	 */
	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}
	/**
	 * @return the professorName
	 */
	public String getProfessorName() {
		return professorName;
	}
	/**
	 * @param professorName the professorName to set
	 */
	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}
	/**
	 * @return the professorDepartment
	 */
	public String getProfessorDepartment() {
		return professorDepartment;
	}
	/**
	 * @param professorDepartment the professorDepartment to set
	 */
	public void setProfessorDepartment(String professorDepartment) {
		this.professorDepartment = professorDepartment;
	}

}
