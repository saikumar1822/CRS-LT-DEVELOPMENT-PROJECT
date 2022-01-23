package com.lt.crs.service;


import java.util.List;


import com.lt.crs.dto.ProfessorDto;
import com.lt.crs.model.Course;
import com.lt.crs.model.Professor;
import com.lt.crs.model.Student;
import com.lt.crs.model.StudentGrade;
/**
 * 
 * @author saikumar
 * 
 *AdminService interface includes the admin operations
 */
public interface AdminService {
/**
 * 
 * @param course
 * @return
 */
	Course saveCourse(Course course);
/**
 * 
 * @param id
 */
	void deleteCourseById(int id);
	/**
	 * 
	 * @param professorDto
	 * @return
	 */

	Professor saveProfessor(ProfessorDto professorDto);
/**
 * 
 * @param professorId
 */
	void deleteProfessorById(int professorId);
	/**
	 * 
	 * @param studentId
	 * @return
	 */

	Student approveStudent(int studentId);
	/**
	 * 
	 * @param studentId
	 * @return
	 */

	Student rejectStudent(int studentId);

/**
 * 
 * @return
 */
	List<StudentGrade> generateReportCard();

}
