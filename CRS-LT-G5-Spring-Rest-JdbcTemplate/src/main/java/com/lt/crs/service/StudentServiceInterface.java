package com.lt.crs.service;

import java.util.List;

import com.lt.crs.dto.StudentCourseDetails;

import com.lt.crs.model.Course;
import com.lt.crs.model.Student_Course;


/**
 * 
 * @author Diwakar
 * Student Services Interface
 *
 */
public interface StudentServiceInterface {

	public String addCourseToTheStudent(Student_Course student);

	public String dropCourse(int studentId, int courseId);

	public List<StudentCourseDetails> viewReportCard(int studentId) ;

	public List<StudentCourseDetails> viewEnrolledCourses(int studentId);

	public List<Course> listUnpaidCourses(int studentId);


}
