package com.lt.crs.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.crs.dto.ProfessorDto;
import com.lt.crs.exception.CourseMapFoundException;
import com.lt.crs.exception.CourseNotFoundException;
import com.lt.crs.exception.GradeNotFoundException;
import com.lt.crs.exception.InsufficientCourseDetailsException;
import com.lt.crs.exception.NoReportCardException;
import com.lt.crs.exception.ProfessorNotFoundException;
import com.lt.crs.exception.StudentMapToCourseFoundException;
import com.lt.crs.exception.StudentNotFoundException;
import com.lt.crs.model.Course;
import com.lt.crs.model.CourseProfessor;
import com.lt.crs.model.Professor;
import com.lt.crs.model.Role;
import com.lt.crs.model.Student;
import com.lt.crs.model.StudentGrade;
import com.lt.crs.model.StudentCourse;
import com.lt.crs.model.User;
import com.lt.crs.repository.CourseProfessorRepository;
import com.lt.crs.repository.CourseRepository;
import com.lt.crs.repository.ProfessorRepository;
import com.lt.crs.repository.RoleRepository;
import com.lt.crs.repository.StudentCourseRepository;
import com.lt.crs.repository.StudentGradeRepository;
import com.lt.crs.repository.StundentRepository;
import com.lt.crs.repository.UserRepository;

/**
 * 
 * @author saikumar AdminServiceImpl class includes the admin operations
 *
 */
@Service
public class AdminServiceImpl implements AdminService {

	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
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
	StudentGradeRepository studentGradeRepository;

	@Autowired
	StudentCourseRepository studentCourseRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CourseProfessorRepository courseProfessorRepository;

	@Autowired
	RoleRepository roleRepository;

	User user = null;
	Set<Role> roles = null;
	Professor professor = null;
	CourseProfessor courseProfessor = null;

	/**
	 * This save course method first checks the weather course is existed or not if
	 * not save the course as new or updates the course
	 */
	@Override
	public Course saveCourse(Course course) {
		logger.info("inside save course of admin impl class");
		if (course != null && course.getCourseName() != null && course.getCourseCost() != 0) {
			Course course1 = courseRepository.findByCourseName(course.getCourseName());
			if (course1 != null) {
				course1.setCourseCost(course.getCourseCost());
				course1.setCourseName(course.getCourseName());
				logger.info("Student is updated");
				return courseRepository.save(course1);
			}
			logger.info("student is saved");
			return courseRepository.save(course);
		} else {
			logger.error("Missing course Details");
			throw new InsufficientCourseDetailsException(
					"Missing course Details !! ./n Please Enter the All the Required details");
		}
	}

	/**
	 * This deleteCourseById method checks the students are registered or not if
	 * less students then removes the course
	 */
	@Override
	public void deleteCourseById(int id) {
		logger.info("Inside Delete course method course id: " + id);
		int count = studentCourseRepository.countByCourseId(id);
		if (count < 3) {
			try {
				courseRepository.deleteById(id);
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new CourseNotFoundException("Course not found with this ID: " + " " + id);
			}
		} else {
			logger.error("You can not remove the course as no of registered students are greater than 3");
			throw new StudentMapToCourseFoundException(
					"You can not remove the course as no of registered students are greater than 3");
		}

	}

	/**
	 * this saveProfessor method saves the professor details into professor table
	 * 
	 */

	@Override
	public Professor saveProfessor(ProfessorDto professorDto) {
		logger.info("Inside save professor method " + " " + professorDto);
		if (professorDto != null && professorDto.getName() != null && professorDto.getDept() != null) {
			professor = new Professor();

			professor.setProfessorDepartment(professorDto.getDept());
			professor.setProfessorName(professorDto.getName());
			professor = professorRepository.save(professor);
			user = new User();
			user.setId(professor.getProfessorId());
			user.setUsername(professor.getProfessorName());
			user.setPassword(professorDto.getPassword());
			roles = new HashSet<>();
			Role role = roleRepository.findById(3).get();
			roles.add(role);
			user.setRoles(roles);
			userRepository.save(user);
			courseProfessor = new CourseProfessor();
			courseProfessor.setCourseId(professorDto.getCourseId());
			courseProfessor.setProfessorId(professor.getProfessorId());
			courseProfessorRepository.save(courseProfessor);
			return professor;
		} else {
			logger.error("Missing professor details");
			throw new ProfessorNotFoundException(
					"Missing professor Details !! ./n Please Enter the All the Required details");
		}
	}

	/**
	 * deleteProfessorById method removes the professor if professor is not assigned
	 * to teach any course
	 */

	@Override
	public void deleteProfessorById(int professorId) {
		logger.info("Inside delete professor method");
		List<CourseProfessor> courseProfessors = courseProfessorRepository.findByProfessorId(professorId);
		if (!courseProfessors.isEmpty()) {
			logger.error("Sorry you cant remove the Professor who has been assigned to teach course");
			throw new CourseMapFoundException(
					"Sorry you cant remove the Professor who has been assigned to teach course");
		}

		try {
			professorRepository.deleteById(professorId);
			// userRepository.deleteById(professorId);
		} catch (Exception e) {
			logger.error("Professor not found with this ID: " + " " + professorId);
			throw new CourseNotFoundException("Professor not found with this ID: " + " " + professorId);
		}
		logger.info("end of deleteProfessorById");
	}

	/**
	 * Approving the student registration by the admin and takes the student id as
	 * the input
	 */
	@Override
	public Student approveStudent(int studentId) {
		logger.info("inside approve student method");
		Student student = stundentRepository.findById(studentId).orElseThrow(
				() -> new StudentNotFoundException(" student is not found with this id" + " " + studentId));

		student.setApproved(true);
		student = stundentRepository.save(student);
		// add the role to student in user table
		Role role = roleRepository.findById(2).get();
		user = new User();
		user.setId(student.getStudentID());
		user.setUsername(student.getStudentName());
		user.setPassword(student.getPassword());
		roles = new HashSet<>();
		/*
		 * role = new Role(); role.setName("student");
		 */
		roles.add(role);
		user.setRoles(roles);
		userRepository.save(user);
		logger.info("end of approve student method");
		return student;
	}

	/**
	 * Rejecting the student Registration by the admin
	 */
	@Override
	public Student rejectStudent(int studentId) {
		logger.info("Inside rejectStudent method");
		Student student = stundentRepository.findById(studentId).orElseThrow(
				() -> new StudentNotFoundException(" student is not found with this id" + " " + studentId));

		List<StudentCourse> list = studentCourseRepository.findByStudentId(studentId);
		if (!list.isEmpty()) {
			logger.error("Sorry you cant remove the Student  who has been assigned to take the  course");
			throw new CourseMapFoundException(
					"Sorry you cant remove the Student  who has been assigned to take the  course");
		}

		student.setApproved(false);
		student = stundentRepository.save(student);
		logger.info("end of reject student method");
		return student;
	}

	/**
	 * Generating the Report card for all the students
	 */
	@Override
	public List<StudentGrade> generateReportCard() {
		logger.info("Inside generateReport card");
		List<StudentGrade> grades = studentGradeRepository.findAll();
		if (grades.size() == 0) {
			throw new NoReportCardException("Report card not found");
		}
		List<Integer> gradeIds = grades.stream().map(m -> m.getGradeId()).collect(Collectors.toList());
		for (int gradeId : gradeIds) {
			StudentGrade studentGrade = studentGradeRepository.findById(gradeId)
					.orElseThrow(() -> new GradeNotFoundException("Grade not found with this id:" + gradeId));
			studentGrade.setAdminGenaratedReportcard(true);
			studentGradeRepository.save(studentGrade);

		}
		logger.info("end of generateReport card method");
		return grades;

	}
}
