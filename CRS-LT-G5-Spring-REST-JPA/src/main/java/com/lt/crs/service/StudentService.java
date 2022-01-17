package com.lt.crs.service;

import java.util.List;

import com.lt.crs.dto.CourseProfessorDto;
import com.lt.crs.dto.PaymentDto;
import com.lt.crs.dto.EnrolledCourseDto;
import com.lt.crs.model.Payment;
import com.lt.crs.model.StudentGrade;
import com.lt.crs.model.StudentCourse;
/**
 * 
 * @author saikumar
 *StudentService interface includes student abstract methods
 */
public interface StudentService {
	/**
	 * 
	 * @param student
	 */
	public void addCourseToTheStudent(StudentCourse student) ;
	/**
	 * 
	 * @param studentId
	 * @param courseId
	 */
	public void dropCourse(int studentId, int courseId);
	/**
	 * 
	 * @param studentId
	 * @return
	 */
	public List<EnrolledCourseDto> viewEnrolledCourses(int studentId) ;
	/**
	 * 
	 * @param studentId
	 * @return
	 */
	public List<StudentGrade> viewReportCard(int studentId);
	/**
	 * 
	 * @param paymentDto
	 * @return
	 */
	public Payment makePayment(PaymentDto paymentDto);
	/**
	 * 
	 * @return
	 */
	public List<CourseProfessorDto> getAllCourses();
	

}
