package com.lt.crs.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lt.crs.dto.ProfessorDto;
import com.lt.crs.model.Course;
import com.lt.crs.model.Professor;
import com.lt.crs.model.Student;
import com.lt.crs.model.StudentGrade;
import com.lt.crs.service.AdminService;

/**
 * This is AdminController class where admin related end points are there
 * 
 * @author saikumar
 *
 */
@RestController

public class AdminController {
	public static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	AdminService adminService;

	/**
	 * save course restEndPoint is used by the admin to save the new course details
	 * into db
	 * 
	 * @param course
	 * @return
	 */
	//@PostMapping("/course")
	@RequestMapping( method = RequestMethod.POST,value = "/course")
	public ResponseEntity<String> saveCourse(@RequestBody Course course) {
		logger.info("In add course method Admin controller :" + course);
		course = adminService.saveCourse(course);
		logger.info("Course is saved with Id:" + course.getCourseId());
		return new ResponseEntity<String>("course is saved successfully With Id" + " " + course.getCourseId(),
				HttpStatus.OK);
	}

	/**
	 * Delete the course by course id by the admin
	 * 
	 * @param id
	 * @return
	 */

	//@DeleteMapping("/course/{id}")
	@RequestMapping( method = RequestMethod.DELETE,value = "/course/{id}")
	public ResponseEntity<String> deleteCourseById(@PathVariable int id) {
		logger.info("In delete course method Admin controller :" + id);
		adminService.deleteCourseById(id);
		logger.info("Course is deleted Id:" + id);
		return new ResponseEntity<String>("course is deleted successfully With Id" + " " + id, HttpStatus.OK);
	}

	/**
	 * Admin adds the professor
	 * 
	 * @param professorDto
	 * @return
	 */
	//@PostMapping("/professor")
	@RequestMapping( method = RequestMethod.POST,value = "/professor")
	public ResponseEntity<String> saveProfessor(@RequestBody ProfessorDto professorDto) {
		logger.info("In add Professor method Admin controller :" + professorDto);
		Professor professor = adminService.saveProfessor(professorDto);
		logger.info("Professor is saved with Id :" + professor.getProfessorId());
		return new ResponseEntity<String>(
				"professor is saved successfully with Id :" + " " + professor.getProfessorId(), HttpStatus.OK);
	}

	/**
	 * Deletes the professor from the professor table
	 * 
	 * @param professorId
	 * @return
	 */

	//@DeleteMapping("/professor/{professorId}")
	@RequestMapping( method = RequestMethod.DELETE,value = "/professor/{professorId}")
	public ResponseEntity<String> deleteProfessorById(@PathVariable int professorId) {
		logger.info("In Remove professor method admin controller class");
		adminService.deleteProfessorById(professorId);
		logger.info("Professor is deleted with Id :" + professorId);
		return new ResponseEntity<String>("professor is deleted successfully with Id" + " " + professorId,
				HttpStatus.OK);
	}

	/**
	 * Admin approves the admin registration
	 * 
	 * @param studentId
	 * @return
	 */

	//@GetMapping("/approveStudent")
	@RequestMapping( method = RequestMethod.GET,value = "/approveStudent")
	public ResponseEntity<String> approveStudent(@RequestParam int studentId) {
		logger.info("In approveStudent method admin controller class");
		Student student = adminService.approveStudent(studentId);
		logger.info("Approved student Id :"+studentId);
		return new ResponseEntity<String>("Student has been Approved with this Id:" + " " + student.getStudentID(),
				HttpStatus.OK);
	}

	/**
	 * This RestEndPoint is for Rejecting the student registration
	 * 
	 * @param studentId
	 * @return
	 */

	//@DeleteMapping("/rejectStudent")
	@RequestMapping( method = RequestMethod.DELETE,value = "/rejectStudent/{studentId}")
	public ResponseEntity<String> rejectStudent(@PathVariable int studentId) {
		logger.info("In rejectStudent method admin controller class");
		Student student = adminService.rejectStudent(studentId);
		logger.info("Rejected student Id:"+studentId);
		return new ResponseEntity<String>("Student has been rejected with this Id:" + " " + student.getStudentID(),
				HttpStatus.OK);
	}

	/**
	 * This endpoint is used for genarating the report card for the students by the
	 * admin.
	 * 
	 * @return Report card
	 */
	//@GetMapping("/generateReportCard")
	@RequestMapping( method = RequestMethod.GET,value = "/generateReportCard")
	public ResponseEntity<List<StudentGrade>> generateReportCard() {
		logger.info("In generateReportCard method admin controller class");
		List<StudentGrade> grades = adminService.generateReportCard();
		logger.info("Generating Report card"+grades);
		return new ResponseEntity<List<StudentGrade>>(grades, HttpStatus.OK);
	}

}
