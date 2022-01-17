package com.lt.crs.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lt.crs.dto.CourseProfessorDto;
import com.lt.crs.dto.PaymentDto;
import com.lt.crs.dto.EnrolledCourseDto;
import com.lt.crs.model.Payment;
import com.lt.crs.model.StudentGrade;
import com.lt.crs.model.StudentCourse;
import com.lt.crs.service.StudentService;
/**
 * 
 * @author saikumar
 * 
 *StudentController class includes all the rest endpoints of student operations
 */
@RestController
@RequestMapping("/student")
public class StudentController {
	public static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	StudentService studentService;
	/**
	 * 
	 * @param student
	 * @return
	 */
//	@PostMapping("/addCourse")
	@RequestMapping( method = RequestMethod.POST,value = "/addCourse")
	public ResponseEntity<String> addCourse(@RequestBody StudentCourse student) {
		logger.info("Inside add course method in Student controller class");
		studentService.addCourseToTheStudent(student);
		logger.info("End of add course method in student controller");
		return new ResponseEntity<String>("SuccessFully Added", HttpStatus.OK);
	}
	/**
	 * 
	 * @return CourseProfessorDto
	 */
	//@GetMapping("/courses")
	@RequestMapping( method = RequestMethod.GET,value = "/courses")
	public ResponseEntity<List<CourseProfessorDto>> getAllCourese() {
		logger.info("Inside getAllcourses method in student ctrl class");
	List<CourseProfessorDto> courses=studentService.getAllCourses();
	logger.info("End of getAllCourese method in student controller :"+courses);

	 return new ResponseEntity <List<CourseProfessorDto>>(courses, HttpStatus.OK);
		
	}
	/**
	 * 
	 * @param studentId
	 * @param courseId
	 * @return
	 */
	
	//@DeleteMapping("/dropCourse")
	@RequestMapping( method = RequestMethod.DELETE,value = "/dropCourse")
	public ResponseEntity<String> dropCourse(@RequestParam int studentId ,@RequestParam int  courseId) {
		logger.info("Inside dropCourse method in student ctrl class");

		studentService.dropCourse(studentId ,courseId);
		logger.info("End of dropCourse method in student controller ");

		return new ResponseEntity<String>("SuccessFully Deleted", HttpStatus.OK);
	}
	/**
	 * 
	 * @param studentId
	 * @return
	 */
	//@GetMapping("/EnrolledCourses")
	@RequestMapping( method = RequestMethod.GET,value = "/EnrolledCourses")
	public ResponseEntity<List<EnrolledCourseDto>> viewEnrolledCourses(@RequestParam int studentId) {
		logger.info("Inside viewEnrolledCourses method in student ctrl class");

		List<EnrolledCourseDto> viewStudentsDto=studentService.viewEnrolledCourses(studentId);
		logger.info("End of viewEnrolledCourses method in student controller :"+viewStudentsDto);

		return new ResponseEntity<List<EnrolledCourseDto>>(viewStudentsDto, HttpStatus.OK);
	}
	/**
	 * 
	 * @param studentId
	 * @return
	 */
	//@GetMapping("/reportCard")
	@RequestMapping( method = RequestMethod.GET,value = "/reportCard")

	public ResponseEntity<List<StudentGrade>> viewReportCard(@RequestParam int studentId) {
		logger.info("Inside viewReportCard method in student ctrl class");

		List<StudentGrade> studentGrades=studentService.viewReportCard(studentId);
		logger.info("End of viewReportCard method in student controller :"+studentGrades);

		return new ResponseEntity<List<StudentGrade>>(studentGrades, HttpStatus.OK);
	}
	/**
	 * 
	 * @param paymentDto
	 * @return
	 */
	//@PostMapping("/payment")
	@RequestMapping( method = RequestMethod.POST,value = "/payment")
	public ResponseEntity<String> payment(@RequestBody PaymentDto paymentDto) {
		logger.info("Inside payment method in student ctrl class");

		Payment payment=studentService.makePayment(paymentDto);
		logger.info("End of payment method in student controller :"+payment);

		return new ResponseEntity<String>("Payment Done Successfully with Payment ID: "+payment.getPaymentId()+" "+"Amount :"+payment.getAmount(), HttpStatus.OK);
	}
	
	

}
