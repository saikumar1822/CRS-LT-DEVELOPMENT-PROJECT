package com.lt.crs.model;



/**
 * @author Abdul,Sai kumar,Rohan,Siva,Nikhil
 *
 */

public class Professor {
	
	private int professorId;
	private String professorName;
	private String professorPassword;
	private String professorDepartment;

	public Professor(int pid,String professorName, String pass,String department) {
			this.professorId = pid;
		this.professorName = professorName;
		this.professorPassword=pass;
		this.professorDepartment = department;
	

	}
	

	public Professor() {
		// TODO Auto-generated constructor stub
	}


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

	public String getProfessorPassword() {
		return professorPassword;
	}

	public void setProfessorPassword(String professorPassword) {
		this.professorPassword = professorPassword;
	}

	public String getProfessorDepartment() {
		return professorDepartment;
	}

	public void setProfessorDepartment(String professorDepartment) {
		this.professorDepartment = professorDepartment;
	}

	
}
