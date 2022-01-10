
package com.lt.crs.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lt.crs.dto.StudentCourseDetails;
import com.lt.crs.model.Student_Course;
import com.lt.crs.service.ProfessorServiceInterface;


/**
 * 
 * @author Siva,Abdul
 * This is Professsor Controller class 
 */

@RestController
@CrossOrigin
//@RequestMapping("/professorService")
public class ProfessorController {

	
	@Autowired
	private ProfessorServiceInterface professorDAO;
	
	@RequestMapping( method = RequestMethod.GET,value = "/list-student-to-add-grade")
	@ResponseBody
	public ResponseEntity<List<Student_Course>> getListOfStudentsToAddGrade(@RequestParam int pid) {

		List<Student_Course> li=professorDAO.listOfStudentsToAddGrades(pid);
		return new ResponseEntity<List<Student_Course>>(li, HttpStatus.OK);
	}

	@RequestMapping( method = RequestMethod.GET,value = "/list-student-to-update-grade")
	@ResponseBody
	public ResponseEntity<List<Student_Course>> getListOfStudentsToUpdateGrade(@RequestParam int pid) {

		List<Student_Course> li=professorDAO.listOfStudentsToUpdateGrades(pid);
		return new ResponseEntity<List<Student_Course>>(li, HttpStatus.OK);
	}
	
	
	@RequestMapping( method = RequestMethod.GET,value = "/view-enrolled-students/{id}")
	@ResponseBody
	public ResponseEntity<List<StudentCourseDetails>> getEnrolledStudents(@PathVariable("id") int id) {

		List<StudentCourseDetails> li=professorDAO.viewEnrolledStudents(id);
		return new ResponseEntity<List<StudentCourseDetails>>(li, HttpStatus.OK);
	}
	
	
	
	@RequestMapping( method = RequestMethod.POST,value = "/add-grades")
	@ResponseBody
	public ResponseEntity<String> addGradestoStudent(@RequestBody Student_Course o,@RequestParam double marks ) {
		
		String res=professorDAO.addGrades(o,marks);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
	
	
	
	
	
	

}
