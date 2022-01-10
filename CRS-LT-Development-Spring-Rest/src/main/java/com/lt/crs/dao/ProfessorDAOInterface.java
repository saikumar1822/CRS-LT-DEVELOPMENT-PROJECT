/**
 * 
 */
package com.lt.crs.dao;

import java.util.List;

import com.lt.crs.dto.StudentCourseDetails;
import com.lt.crs.model.Professor;
import com.lt.crs.model.Student;
import com.lt.crs.model.Student_Course;

/**
 * 
 * @author Sai
 *
 */

public interface ProfessorDAOInterface {

	public List<StudentCourseDetails> viewEnrolledStudents(int id);
	public String addGrades(Student_Course o, double marks);
	List<Student_Course> listOfStudentsToAddGrades(int pid);
	public List<Student_Course> listOfStudentsToUpdateGrades(int pid);
}
