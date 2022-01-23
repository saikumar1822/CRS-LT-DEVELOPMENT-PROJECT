/**
 * 
 */
package com.lt.crs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.crs.dto.StudentCourseDetailsDto;
import com.lt.crs.exception.CourseNotFoundException;
import com.lt.crs.exception.ProfessorNotFoundException;
import com.lt.crs.exception.StudentNotFoundException;
import com.lt.crs.model.Course;
import com.lt.crs.model.Professor;
import com.lt.crs.model.Student;
import com.lt.crs.model.StudentGrade;
import com.lt.crs.model.StudentCourse;
import com.lt.crs.repository.CourseRepository;
import com.lt.crs.repository.ProfessorRepository;
import com.lt.crs.repository.StudentCourseRepository;
import com.lt.crs.repository.StudentGradeRepository;
import com.lt.crs.repository.StundentRepository;

/**
 * @author saikumar ProfessorServiceImpl class have all the implementation of
 *         professor operations
 */
@Service
public class ProfessorServiceImpl implements ProfessorService {
	public static final Logger logger = LoggerFactory.getLogger(ProfessorServiceImpl.class);

	/**
	 * Injecting the required dependencies using @autowired annotation
	 */
	@Autowired
	StundentRepository stundentRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	ProfessorRepository professorRepository;
	@Autowired
	StudentGradeRepository studentGradeReposiotry;

	@Autowired
	StudentCourseRepository studentCourseRepository;
	StudentCourseDetailsDto scd = null;
	List<StudentCourseDetailsDto> lscd = null;

	/**
	 * viewEnrolledStudents
	 * 
	 * @param professorId
	 * @return List of students registered for the particular professors course
	 */
	public List<StudentCourseDetailsDto> viewEnrolledStudents(int professorId) {
		// check weather professor id is correct or not
		logger.info("inside view enrolled students method and the professor id:" + professorId);

		@SuppressWarnings("unused")
		Professor professor = professorRepository.findById(professorId).orElseThrow(
				() -> new ProfessorNotFoundException("professor not found with this id: " + " " + professorId));

		// get enrolled student details
		List<StudentCourse> sc = studentCourseRepository.findByProfessorId(professorId);
		logger.info("finding the course ids using java 8 stream and map functions");
		List<Integer> courseIds = sc.stream().map(m -> m.getCourseId()).collect(Collectors.toList());
		logger.info("finding the student ids using java 8 stream and map functions");
		List<Integer> studentIds = sc.stream().map(m -> m.getStudentId()).collect(Collectors.toList());
		lscd = new ArrayList<StudentCourseDetailsDto>();

		int i = 0;
		for (int courseId : courseIds) {
			scd = new StudentCourseDetailsDto();
			Course course = courseRepository.findById(courseId).get();

			Student student = stundentRepository.findById(studentIds.get(i)).get();
			i++;
			scd.setStudentId(student.getStudentID());
			scd.setStudentName(student.getStudentName());
			scd.setCourseId(course.getCourseId());
			scd.setCourseName(course.getCourseName());
			lscd.add(scd);

		}
		logger.info("end of view enrolled student method");
		return lscd;
	}

	/**
	 * @param studentGrade as studentGrade entity And adds the grade to the student
	 */
	public String addGradeForStudent(StudentGrade studentGrade) {
		logger.info("Inside add grade for student");
		Student student = stundentRepository.findById(studentGrade.getStudentId())
				.orElseThrow(() -> new StudentNotFoundException(
						" student is not found with this id" + " " + studentGrade.getStudentId()));

		@SuppressWarnings("unused")
		Course course = courseRepository.findById(studentGrade.getCourseId()).orElseThrow(
				() -> new CourseNotFoundException("course not found with this id" + " " + studentGrade.getCourseId()));

		@SuppressWarnings("unused")
		Professor professor = professorRepository.findById(studentGrade.getProfessorId())
				.orElseThrow(() -> new ProfessorNotFoundException(
						"professor not found with this id: " + " " + studentGrade.getProfessorId()));
		StudentGrade studentGrade1 = studentGradeReposiotry.findByCourseIdAndStudentId(studentGrade.getCourseId(),
				studentGrade.getStudentId());
		if (studentGrade1 != null) {
			studentGrade1.setAdminGenaratedReportcard(false);
			studentGrade1.setMark(studentGrade.getMark());
			studentGrade1.setGrade(findingGrade(studentGrade.getMark()));
			studentGradeReposiotry.save(studentGrade1);
			
		} else {
			// finding the grade for the student based on mark
			studentGrade.setAdminGenaratedReportcard(false);
			studentGrade.setGrade(findingGrade(studentGrade.getMark()));
			studentGradeReposiotry.save(studentGrade);
		}
		logger.info("end of add grade for student");
		return "" + student.getStudentID();

	}
/**
 * Finding the grade based on mark
 * @param mark
 * @return
 */
	private static String findingGrade(double mark) {
		if (mark >= 0 && mark < 50) {
			return "Fail";
		} else if (mark >= 50 && mark < 60) {
			return "E";
		} else if (mark >= 60 && mark < 70) {
			return "D";
		} else if (mark >= 70 && mark < 80) {
			return "C";
		} else if (mark >= 80 && mark < 90) {
			return "B";
		} else {
			return "A";
		}

	}

}
