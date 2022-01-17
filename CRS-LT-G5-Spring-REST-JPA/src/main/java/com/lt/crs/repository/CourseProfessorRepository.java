/**
 * 
 */
package com.lt.crs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lt.crs.model.CourseProfessor;

/**
 * @author saikumar
 *
 */
public interface CourseProfessorRepository extends JpaRepository<CourseProfessor, Integer> {

	List<CourseProfessor> findByCourseIdAndProfessorId(int courseId, int professorId);

	List<CourseProfessor> findByProfessorId(int professorId);

}
