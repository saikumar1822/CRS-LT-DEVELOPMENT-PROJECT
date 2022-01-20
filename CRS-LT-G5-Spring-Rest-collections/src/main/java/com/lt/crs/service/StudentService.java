package com.lt.crs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lt.crs.bean.Course;
import com.lt.crs.bean.Professor;
import com.lt.crs.bean.Student;
import com.lt.crs.bean.StudentGrade;
import com.lt.crs.bean.Student_Course;
import com.lt.crs.dto.ViewStudentsDto;
import com.lt.crs.exception.CourseDuplicationException;
import com.lt.crs.exception.CourseNotFoundException;
import com.lt.crs.exception.NoCourseRegisterException;
import com.lt.crs.exception.NoReportCardException;
import com.lt.crs.exception.ProfessorNotFoundException;
import com.lt.crs.exception.StudentNotFoundException;

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

	public List<StudentGrade> viewReportCard(int studentId) {
		// TODO Auto-generated method stub
		return studDAO.viewReportCard(studentId);
	}

	public ViewStudentsDto viewEnrolledCourses(int studentId) {
		// TODO Auto-generated method stub
		return studDAO.viewEnrolledCourses(studentId);
	}

	@Override
	public String payment(int studentId) {
		// TODO Auto-generated method stub
		return studDAO.payment(studentId);
	}
	
}