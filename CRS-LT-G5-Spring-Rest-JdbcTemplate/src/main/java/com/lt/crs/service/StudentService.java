package com.lt.crs.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.crs.dto.StudentCourseDetails;
import com.lt.crs.model.Course;
import com.lt.crs.model.Student_Course;
/**
 * 
 * @author Sai
 * 
 *
 */

@Service
public class StudentService implements StudentServiceInterface{

	@Autowired
	com.lt.crs.dao.StudentDAOInterface studDAO;

	public String addCourseToTheStudent(Student_Course student) {
		// TODO Auto-generated method stub
		return studDAO.addCourseToTheStudent(student);
		
	}

	public String dropCourse(int studentId, int courseId) {
		// TODO Auto-generated method stub
		return studDAO.dropCourse(studentId, courseId);
		
	}

	public List<StudentCourseDetails> viewReportCard(int studentId) {
		// TODO Auto-generated method stub
		return studDAO.viewReportCard(studentId);
	}

	public List<StudentCourseDetails> viewEnrolledCourses(int studentId) {
		// TODO Auto-generated method stub
		return studDAO.viewEnrolledCourses(studentId);
	}

	@Override
	public List<Course> listUnpaidCourses(int studentId) {
		// TODO Auto-generated method stub
		return studDAO.listUnpaidCourses(studentId);
	}


	
}