package com.lt.crs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lt.crs.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	Course findByCourseName(String courseName);

}
