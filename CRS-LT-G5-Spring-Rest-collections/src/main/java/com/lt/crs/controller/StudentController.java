package com.lt.crs.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lt.crs.bean.StudentGrade;
import com.lt.crs.bean.Student_Course;
import com.lt.crs.dto.ViewStudentsDto;
import com.lt.crs.service.StudentService;
import com.lt.crs.service.StudentServiceInterface;

@RestController
public class StudentController {
	@Autowired
	StudentServiceInterface studentService;
	
	

	@RequestMapping(method=RequestMethod.POST,value="/addCourse")
	public ResponseEntity<String> addCourse(@RequestBody Student_Course student) {
		String res=studentService.addCourseToTheStudent(student);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/dropCourse")

	public ResponseEntity<String> dropCourse(@RequestParam int studentId ,@RequestParam int  courseId) {
		String res=studentService.dropCourse(studentId ,courseId);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/viewEnrolledCourses")

	public ResponseEntity<ViewStudentsDto> viewEnrolledCourses(@RequestParam int studentId) {
		ViewStudentsDto viewStudentsDto=studentService.viewEnrolledCourses(studentId);
		return new ResponseEntity<ViewStudentsDto>(viewStudentsDto, HttpStatus.OK);
	}
	@RequestMapping(method=RequestMethod.GET,value="/viewReportCard")
	public ResponseEntity<List<StudentGrade>> viewReportCard(@RequestParam int studentId) {
		List<StudentGrade> studentGrades=studentService.viewReportCard(studentId);
		return new ResponseEntity<List<StudentGrade>>(studentGrades, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/payment")

	public ResponseEntity<String> payment(@RequestParam int studentId) {
		String  res=studentService.payment(studentId);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}	
	

}
