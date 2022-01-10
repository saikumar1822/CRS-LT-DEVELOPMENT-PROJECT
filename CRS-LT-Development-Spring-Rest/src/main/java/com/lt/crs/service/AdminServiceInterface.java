package com.lt.crs.service;

import java.util.List;

import com.lt.crs.dto.StudentCourseDetails;
import com.lt.crs.model.Course;
import com.lt.crs.model.Payment;
import com.lt.crs.model.Professor;
import com.lt.crs.model.Student;

/**
 * @author Abdul
 *
 */
public interface AdminServiceInterface {
	public List<Professor> list();
	public List<Course> listOfCourses();
	public String createProfessor(Professor prof1);
	public String addCourse(Course c);
	public String deleteProfessor(int id) ;
	public String approveStudent(int sid);
	public List<Student> listOfUnApprovedStudents();
	public String deleteStudent(int sid);
	List<StudentCourseDetails> viewAllReportCard();
	public List<Payment> listToDeletePaidStudentIfRequested();
	String deleteCourseForASingleStudent(int cid, int sid);
	public List<Course> listCourseWithLessThanThreeStudents();
	public String mapCourseToProfessor(int cid, int pid);
	List<String> deleteCourse(int cid);
}
