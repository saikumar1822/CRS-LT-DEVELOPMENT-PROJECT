package com.lt.crs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lt.crs.model.StudentGrade;
/**
 * 
 * @author saikumar
 *
 */
@Repository
public interface StudentGradeRepository extends JpaRepository<StudentGrade, Integer> {
/**
 * 
 * @param studentId
 * @return
 */
	List<StudentGrade> findByStudentId(int studentId);

	/**
	 * @param courseId
	 * @param studentId
	 */
	StudentGrade findByCourseIdAndStudentId(int courseId, int studentId);
	

}
