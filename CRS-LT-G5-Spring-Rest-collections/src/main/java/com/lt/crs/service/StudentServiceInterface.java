package com.lt.crs.service;

import java.util.List;

import com.lt.crs.bean.StudentGrade;
import com.lt.crs.bean.Student_Course;
import com.lt.crs.dto.ViewStudentsDto;

public interface StudentServiceInterface {

	public String addCourseToTheStudent(Student_Course student);

	public String dropCourse(int studentId, int courseId);

	public List<StudentGrade> viewReportCard(int studentId) ;

	public ViewStudentsDto viewEnrolledCourses(int studentId);

	public String payment(int studentId);

}
