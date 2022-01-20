package com.lt.crs.dao;

import java.util.List;

import com.lt.crs.bean.StudentGrade;
import com.lt.crs.bean.Student_Course;
import com.lt.crs.dto.ViewStudentsDto;

public interface StudentDAOInterface {

	public String addCourseToTheStudent(Student_Course student);

	public String dropCourse(int studentId, int courseId);

	public List<StudentGrade> viewReportCard(int studentId) ;

	public ViewStudentsDto viewEnrolledCourses(int studentId);

	public String payment(int studentId);

	
}
