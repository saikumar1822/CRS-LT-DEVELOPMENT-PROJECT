package com.lt.crs.service;

import java.util.List;

import com.lt.crs.dto.StudentCourseDetails;
import com.lt.crs.model.Student_Course;

/**
 * @author Diwakar
 *
 */
public interface ProfessorServiceInterface {

	public List<StudentCourseDetails> viewEnrolledStudents(int id);
	public String addGrades(Student_Course o, double marks);
	List<Student_Course> listOfStudentsToAddGrades(int pid);
	public List<Student_Course> listOfStudentsToUpdateGrades(int pid);
}
