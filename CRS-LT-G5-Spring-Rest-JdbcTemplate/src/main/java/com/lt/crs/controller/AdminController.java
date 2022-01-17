package com.lt.crs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lt.crs.dto.StudentCourseDetails;
import com.lt.crs.model.Course;
import com.lt.crs.model.Payment;
import com.lt.crs.model.Professor;
import com.lt.crs.model.Student;


/**
 * 
 * @author Sai,Rohan
 * This is Admin Controller class 
 */

@RestController
public class AdminController {
	
	@Autowired
	com.lt.crs.service.AdminServiceInterface adser;
	
	@RequestMapping( method = RequestMethod.GET,value = "/all-professors")
	@ResponseBody
	public ResponseEntity<List<Professor>> getProfessors() {
			List<Professor> lists=adser.list();
		
		return new ResponseEntity<List<Professor>>(lists,HttpStatus.OK);
	}
	
	@RequestMapping( method = RequestMethod.GET,value = "/all-courses")
	@ResponseBody
	public ResponseEntity<List<Course>> getCourses() {
			List<Course> lists=adser.listOfCourses();
		
		return new ResponseEntity<List<Course>>(lists,HttpStatus.OK);
	}
	
	@RequestMapping( method = RequestMethod.GET,value = "/list-unapproved-students")
	@ResponseBody
	public ResponseEntity<List<Student>> listUnApprovedStudents() {
			List<Student> lists=adser.listOfUnApprovedStudents();
		
		return new ResponseEntity<List<Student>>(lists,HttpStatus.OK);
	
	}

	@RequestMapping(method=RequestMethod.POST,value="/approve-student")

	public ResponseEntity<String> approvalOfStudent(@RequestParam int sid) {
		String res=adser.approveStudent(sid);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/remove-student")
	public ResponseEntity<String> deleteStudent(@RequestParam int sid) {
		String res=adser.deleteStudent(sid);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/add-professor")

	public ResponseEntity<String> addProfessor(@RequestBody Professor prof) {
		String res=adser.createProfessor(prof);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/add-course")

	public ResponseEntity<String> addProfessor(@RequestBody Course c) {
		String res=adser.addCourse(c);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.DELETE,value="/remove-professor")

	public ResponseEntity<String> removeProfessor(@RequestParam int profId) {
		String res=adser.deleteProfessor(profId);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.GET,value="/view-all-report-card")
	public ResponseEntity<List<StudentCourseDetails>> viewAllReportCard() {
		List<StudentCourseDetails> allGrades=adser.viewAllReportCard();
		return new ResponseEntity<List<StudentCourseDetails>>(allGrades, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/list-to-delete-paid-students-if-requested")
	public ResponseEntity<List<Payment>> listToDeletePaidStudentIfRequested() {
		List<Payment> allGrades=adser.listToDeletePaidStudentIfRequested();
		return new ResponseEntity<List<Payment>>(allGrades, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/delete-course-for-single-student")

	public ResponseEntity<String> deleteCourseForSingleStudent(@RequestParam int cid,@RequestParam int sid) {
		String res=adser.deleteCourseForASingleStudent(cid, sid);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/list-courses-with-less-than-three-students")
	public ResponseEntity<List<Course>> getAllCoursesWhichIsToBeDelete() {
		List<Course> allCourses=adser.listCourseWithLessThanThreeStudents();
		return new ResponseEntity<List<Course>>(allCourses, HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.PUT,value="/map-course-to-professor")

	public ResponseEntity<String> addProfessorToTheCourses(@RequestParam int cid,@RequestParam int pid) {
		String res=adser.mapCourseToProfessor(cid, pid);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/delete-course")

	public ResponseEntity<List<String>> a(@RequestParam int cid) {
		List<String> res=adser.deleteCourse(cid);
		return new ResponseEntity<List<String>>(res, HttpStatus.OK);
	}
	
	
	
}
