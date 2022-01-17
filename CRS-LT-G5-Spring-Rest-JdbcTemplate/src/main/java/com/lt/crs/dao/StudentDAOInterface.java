package com.lt.crs.dao;

import java.util.List;

import com.lt.crs.dto.StudentCourseDetails;
import com.lt.crs.dto.ViewStudentsDto;
import com.lt.crs.model.Course;
import com.lt.crs.model.StudentGrade;
import com.lt.crs.model.Student_Course;



/**
 * 
 * @author Diwakar
 * This StudentDao interface
 *
 */
public interface StudentDAOInterface {

	public String addCourseToTheStudent(Student_Course student);

	public String dropCourse(int studentId, int courseId);

	public List<StudentCourseDetails> viewReportCard(int studentId) ;

	public List<StudentCourseDetails> viewEnrolledCourses(int studentId);

	public List<Course> listUnpaidCourses(int studentId);


	
}
