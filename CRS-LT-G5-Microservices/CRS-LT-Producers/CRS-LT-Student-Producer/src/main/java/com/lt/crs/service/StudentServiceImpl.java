package com.lt.crs.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.crs.dto.CourseProfessorDto;
import com.lt.crs.dto.PaymentDto;
import com.lt.crs.dto.EnrolledCourseDto;
import com.lt.crs.exception.CourseDuplicationException;
import com.lt.crs.exception.CourseNotFoundException;
import com.lt.crs.exception.GradeFoundException;
import com.lt.crs.exception.InsufficientCardDetailsException;
import com.lt.crs.exception.MaxCourseRegistered;
import com.lt.crs.exception.NoCourseRegisterException;
import com.lt.crs.exception.NoReportCardException;
import com.lt.crs.exception.PaymentFoundException;
import com.lt.crs.exception.ProfessorNotFoundException;
import com.lt.crs.exception.ProfessorNotMappedToCourseException;
import com.lt.crs.exception.StudentNotFoundException;
import com.lt.crs.model.Course;
import com.lt.crs.model.CourseProfessor;
import com.lt.crs.model.Notification;
import com.lt.crs.model.Payment;
import com.lt.crs.model.Professor;
import com.lt.crs.model.Student;
import com.lt.crs.model.StudentGrade;
import com.lt.crs.model.StudentCourse;
import com.lt.crs.repository.CourseProfessorRepository;
import com.lt.crs.repository.CourseRepository;
import com.lt.crs.repository.NotificationRepository;
import com.lt.crs.repository.PaymentRepository;
import com.lt.crs.repository.ProfessorRepository;
import com.lt.crs.repository.StudentCourseRepository;
import com.lt.crs.repository.StudentGradeRepository;
import com.lt.crs.repository.StundentRepository;

/**
 * 
 * @author saikumar StudentServiceImpl includes the implementations student
 *         services
 *
 */
@Service
public class StudentServiceImpl implements StudentService {
	public static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

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
	StudentCourseRepository studentCourseRepository;
	@Autowired
	StudentGradeRepository studentGradeRepository;

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	CourseProfessorRepository courseProfessorRepository;

	@Autowired
	NotificationRepository notificationRepository;

	List<Course> courses = null;
	EnrolledCourseDto vs = null;
	List<Professor> professors = null;
	List<CourseProfessorDto> courseProfessorDtos = null;
	List<EnrolledCourseDto> enrolledCourseDtos = null;
	EnrolledCourseDto enrolledCourseDto = null;

	/**
	 * addCourseToTheStudent method adds the course to the student where different
	 * conditions will be checked weather admin is approved the student course is
	 * available or not is any professor assigned to teach the course or not
	 */
	@SuppressWarnings("unused")
	public void addCourseToTheStudent(StudentCourse student) {
		logger.info("inside addCourseToTheStudent method");
		Student student1 = stundentRepository.findById(student.getStudentId())
				.orElseThrow(() -> new StudentNotFoundException(
						" student is not found with this id" + " " + student.getStudentId()));
		if (!student1.isApproved()) {
			logger.error("admin not approved this student id: " + student.getStudentId());
			throw new StudentNotFoundException(
					" Admin is not yet approved this Student Id" + " " + student.getStudentId());
		}
		logger.info("fetching course details by passing course id ");
		Course course = courseRepository.findById(student.getCourseId()).orElseThrow(
				() -> new CourseNotFoundException("course not found with this id" + " " + student.getCourseId()));
		logger.info("fetching professor details by passing professor id ");
		Professor professor = professorRepository.findById(student.getProfessorId())
				.orElseThrow(() -> new ProfessorNotFoundException(
						"professor not found with this id: " + " " + student.getProfessorId()));
		logger.info("fetching course professor list details by passing course id and professor id ");
		List<CourseProfessor> courseProfessorList = courseProfessorRepository
				.findByCourseIdAndProfessorId(student.getCourseId(), student.getProfessorId());

		if (courseProfessorList.isEmpty()) {
			logger.error("Professor is not mapped to this course");
			throw new ProfessorNotMappedToCourseException(
					"Professor is not mapped to this course ! you can try For Different professor's course");
		}

		List<StudentCourse> list = studentCourseRepository.findByStudentId(student.getStudentId());
		if (list.size() >= 4) {
			logger.error("Already Registered Maximum courses");
			throw new MaxCourseRegistered("Already Registered Maximum courses");
		}

		StudentCourse sc = studentCourseRepository.findByCourseIdAndStudentId(student.getCourseId(),
				student.getStudentId());
		if (sc != null) {
			logger.error("You have already Resitered the This course " + student.getCourseId());
			throw new CourseDuplicationException("You have already Resitered the This course " + student.getCourseId());
		}

		studentCourseRepository.save(student);
		logger.info("end of addCourseToTheStudent method");
	}

	/**
	 * DropCourse method takes the course id and student id as parameters and drops
	 * the course by the student
	 */

	@SuppressWarnings("unused")
	@Transactional
	public void dropCourse(int studentId, int courseId) {
		logger.info("fetching student details by passing student id " + studentId);
		Student student1 = stundentRepository.findById(studentId).orElseThrow(
				() -> new StudentNotFoundException(" student is not found with this id" + " " + studentId));
		logger.info("fetching course details by passing course id " + courseId);
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new CourseNotFoundException("course not found with this id" + " " + courseId));

		StudentGrade studentGrade = studentGradeRepository.findByCourseIdAndStudentId(courseId, studentId);
		if (studentGrade != null) {
			logger.error("Grade has been already provided");
			throw new GradeFoundException("Grade has been already provided ! sorry u cant remove the course");
		}

		Payment payment = paymentRepository.findByCourseIdAndStudentId(courseId, studentId);
		if (payment != null) {
			throw new PaymentFoundException(
					"Payment has done you cant remove the course ! Please reach out to the admin");
		}

		studentCourseRepository.deleteByStudentIdAndCourseId(studentId, courseId);
		logger.info("end of drop course method");
	}

	/**
	 * viewEnrolledCourses method takes the student id as parameter and displays the
	 * Registered courses
	 */

	public List<EnrolledCourseDto> viewEnrolledCourses(int studentId) {
		logger.info("inside viewEnrolledCourses method");
		int i = 0;
		logger.info("fetching student details");
		@SuppressWarnings("unused")
		Student student1 = stundentRepository.findById(studentId).orElseThrow(
				() -> new StudentNotFoundException(" student is not found with this id" + " " + studentId));
		List<StudentCourse> sc = studentCourseRepository.findByStudentId(studentId);
		if (sc.size() == 0) {
			logger.error("no course registered yet");
			throw new NoCourseRegisterException("No course registered yet");
		}
		List<Integer> courseIds = sc.stream().map(m -> m.getCourseId()).collect(Collectors.toList());
		List<Integer> professorIds = sc.stream().map(m -> m.getProfessorId()).collect(Collectors.toList());
		enrolledCourseDtos = new ArrayList<EnrolledCourseDto>();
		for (int courseId : courseIds) {
			enrolledCourseDto = new EnrolledCourseDto();
			Course course = courseRepository.findById(courseId)
					.orElseThrow(() -> new CourseNotFoundException(" course is not found with this id" + " " + courseId
							+ " " + "may be removed by the admin after student adds the course"));
			enrolledCourseDto.setCourseId(course.getCourseId());
			enrolledCourseDto.setCourseName(course.getCourseName());
			Professor professor = professorRepository.findById(professorIds.get(i++)).orElseThrow(
					() -> new ProfessorNotFoundException(" Professor is not found may be romoved by the admin"));
			enrolledCourseDto.setProfessorId(professor.getProfessorId());
			enrolledCourseDto.setProfessorName(professor.getProfessorName());

			enrolledCourseDtos.add(enrolledCourseDto);
		}
		logger.info("end of viewEnrolledCourses method");
		return enrolledCourseDtos;

	}

	/**
	 * viewReportCard takes the studentId as the parameter and displays the report
	 * card
	 */

	public List<StudentGrade> viewReportCard(int studentId) {
		logger.info("inside view report card method for the follwing student:" + studentId);
		@SuppressWarnings("unused")
		Student student1 = stundentRepository.findById(studentId).orElseThrow(
				() -> new StudentNotFoundException(" student is not found with this id" + " " + studentId));

		List<StudentGrade> grades = studentGradeRepository.findByStudentId(studentId).stream()
				.filter(m -> m.isAdminGenaratedReportcard() == true).toList();
		if (grades.size() == 0) {
			logger.error("report card not found");
			throw new NoReportCardException("Report card not found");
		}
		logger.info("end of view report card method");
		return grades;
	}

	/**
	 * Payment method checks the basic validations and saves the payment information
	 * in the table
	 */
	public Payment makePayment(PaymentDto paymentDto) {
		logger.info("inside payment method");
		if (paymentDto.getCardNo().length() >= 13 && paymentDto.getCardNo().length() <= 16
				&& paymentDto.getExpiryDate() != null) {
			if (paymentDto.getCvv().length() == 3) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
				simpleDateFormat.setLenient(false);
				Date expiry = null;
				try {
					expiry = simpleDateFormat.parse(paymentDto.getExpiryDate());
				} catch (ParseException e) {
					e.getMessage();
				}
				boolean expired = expiry.before(new Date());
				if (expired == true) {
					throw new InsufficientCardDetailsException("Card Already has been Expired");
				} else {

					@SuppressWarnings("unused")
					Student student = stundentRepository.findById(paymentDto.getStudentId())
							.orElseThrow(() -> new StudentNotFoundException(
									" student is not found with this id" + " " + paymentDto.getStudentId()));

					Course course = courseRepository.findById(paymentDto.getCourseId())
							.orElseThrow(() -> new CourseNotFoundException(
									"course not found with this id" + " " + paymentDto.getCourseId()));
					Payment payment = paymentRepository.findByCourseIdAndStudentId(paymentDto.getCourseId(),
							paymentDto.getStudentId());

					if (payment != null) {
						throw new PaymentFoundException(
								"U have already paid the money for the following Course Id:" + payment.getCourseId());

					}
					payment = new Payment();
					payment.setStatus(true);
					payment.setCourseId(paymentDto.getCourseId());
					payment.setStudentId(paymentDto.getStudentId());
					payment.setAmount(course.getCourseCost());
					payment = paymentRepository.save(payment);
					Notification notification = new Notification();
					notification.setCreatedAt(new Date());
					notification.setMessage("Payment done sucessfully");
					notification.setUserId(payment.getStudentId());
					notificationRepository.save(notification);
					logger.info("end of payment method");
					return payment;
				}

			} else {
				logger.error("Enter the valid CVV number");
				throw new InsufficientCardDetailsException("Enter the valid CVV number");
			}

		} else {
			logger.error("enter the valid card number");
			throw new InsufficientCardDetailsException("Enter the valid card number");
		}

	}

	/**
	 * Displaying the available courses to the student so that student can
	 * registered for the student
	 */

	@Override
	public List<CourseProfessorDto> getAllCourses() {
		logger.info("inside get all courses method");
		int i = 0;
		courseProfessorDtos = new ArrayList<CourseProfessorDto>();

		List<CourseProfessor> courseProfessorList = courseProfessorRepository.findAll();
		if (courseProfessorList.isEmpty()) {
			throw new CourseNotFoundException("No courses Found");
		}
		logger.info("find the course id and professor ids using java 8 stream and map function");
		List<Integer> courseIds = courseProfessorList.stream().map(m -> m.getCourseId()).collect(Collectors.toList());
		List<Integer> professorIds = courseProfessorList.stream().map(m -> m.getProfessorId())
				.collect(Collectors.toList());

		for (int courseId : courseIds) {
			CourseProfessorDto courseProfessorDto = new CourseProfessorDto();
			Course course = courseRepository.findById(courseId).get();
			courseProfessorDto.setCourseId(course.getCourseId());
			courseProfessorDto.setCourseName(course.getCourseName());
			Professor professor = professorRepository.findById(professorIds.get(i++)).get();
			courseProfessorDto.setProfessorId(professor.getProfessorId());
			courseProfessorDto.setProfessorName(professor.getProfessorName());
			courseProfessorDtos.add(courseProfessorDto);
		}
		logger.info("end of get all courses method");
		return courseProfessorDtos;
	}

}