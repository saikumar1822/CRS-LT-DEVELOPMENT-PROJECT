package com.lt.crs.business;
/**
 * 
 * @author Diwakar, rohan,sai kumar
 *
 */
public interface StudentInterface {
/**
 * This method is used for enrolling the course
 * @param studentId
 */
	public void addCourse(int studentId);
/**
 * Using this Drop course method student can drop from the course
 * @param studentId
 */
	public void dropCourse(int studentId);
/**
 * student can able to view the enrolled courses
 * @param studentId
 */
	public void viewEnrolledCourse(int studentId);
/**
 * student can view the report card using this method
 * @param studentId
 */
	public void viewReportCard(int studentId);

}
