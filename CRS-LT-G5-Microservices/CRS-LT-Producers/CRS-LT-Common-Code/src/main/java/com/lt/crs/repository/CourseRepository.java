package com.lt.crs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lt.crs.model.Course;
/**
 * 
 * @author saikumar
 *
 */
public interface CourseRepository extends JpaRepository<Course, Integer> {
/**
 * 
 * @param courseName
 * @return Course
 */
	public Course findByCourseName(String courseName);

}
