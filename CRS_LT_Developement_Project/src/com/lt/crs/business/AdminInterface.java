package com.lt.crs.business;
/**
 * 
 * @author Sai kumar,Diwkar,Rohan,Abdul,Nikhil,Siva
 *This is Admin Interface where admin Related methods are declared
 */
public interface AdminInterface {
	
/**
 * Add professor method is used to add the professor by the admin
 * @param
 * @return void
 */
	public void addProfessor();
	/**
	 * Remove professor method is used to Remove the professor by the admin
	 * @param
	 * @return void
	 */

	public void removeProfessor();
	/**
	 * Add student method is used to Approve the student Registration by the admin
	 * @param
	 * @return void
	 */

	public void addStudent();
	/**
	 * Remove student method is used to Reject the student registration by the admin
	 * @param
	 * @return void
	 */

	public void removeStudent();
	/**
	 * addCourse method is used to add the new course by the admin so that students can register fro course
	 * @param
	 * @return void
	 */

	public void addCourse();
	/**
	 * Remove course method is used to remove curse from the course table by the admin
	 * @param
	 * @return void
	 */

	public void removeCourse();
	/**
	 * reportCardGeneration method Generates the reports cards for the students by the admin
	 * @param
	 * @return void
	 */

	public void reportCardGeneration();
	

}
