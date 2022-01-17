package com.lt.crs.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lt.crs.dto.StudentCourseDetails;

import com.lt.crs.model.Course;
import com.lt.crs.model.Payment;
import com.lt.crs.model.Student;
import com.lt.crs.model.Student_Course;
import com.lt.crs.service.StudentRegistrationInterface;
import com.lt.crs.service.StudentServiceInterface;


/**
 * 
 * @author Diwakar,Abdul
 * This is Student Controller class 
 */


@RestController
public class StudentController {
	@Autowired
	StudentServiceInterface studentService;
	@Autowired
	StudentRegistrationInterface studRegSer;
	@Autowired
	com.lt.crs.service.PaymentServiceInterface paySer;
	

	@RequestMapping(method=RequestMethod.POST,value="/enroll-course")
	public ResponseEntity<String> addCourse(@RequestBody Student_Course student) {
		String res=studentService.addCourseToTheStudent(student);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/drop-course")

	public ResponseEntity<String> dropCourse(@RequestParam int studentId ,@RequestParam int  courseId) {
		String res=studentService.dropCourse(studentId ,courseId);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/view-enrolled-courses")

	public ResponseEntity<List<StudentCourseDetails>> viewEnrolledCourses(@RequestParam int studentId) {
		List<StudentCourseDetails> viewStudentsDto=studentService.viewEnrolledCourses(studentId);
		return new ResponseEntity<List<StudentCourseDetails>>(viewStudentsDto, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/unpaid-courses-list")

	public ResponseEntity<List<Course>> viewListOfUnpaidCourses(@RequestParam int studentId) {
		List<Course> viewUnpaidCoursesList=studentService.listUnpaidCourses(studentId);
		return new ResponseEntity<List<Course>>(viewUnpaidCoursesList, HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.GET,value="/view-report-card")
	public ResponseEntity<List<StudentCourseDetails>> viewReportCard(@RequestParam int studentId) {
		List<StudentCourseDetails> studentGrades=studentService.viewReportCard(studentId);
		return new ResponseEntity<List<StudentCourseDetails>>(studentGrades, HttpStatus.OK);
	}
		
	
	

	@RequestMapping(method=RequestMethod.POST,value="/student-registeration")
	public ResponseEntity<String> studentRegistration(@RequestBody Student student){
		String regStatus=studRegSer.studentRegistration(student);
		return new ResponseEntity<String>(regStatus,HttpStatus.CREATED);	
	}
	 
	@RequestMapping(method=RequestMethod.POST,value="/student-payment")
	public ResponseEntity<String> studentPayment(@RequestBody List<Payment> list,@RequestParam int sid){
		String regStatus=paySer.makePayment(list, sid);
		return new ResponseEntity<String>(regStatus,HttpStatus.CREATED);	
	}
	 
	

}
