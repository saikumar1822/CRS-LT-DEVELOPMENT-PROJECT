package com.lt.crs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lt.crs.model.Student;
/**
 * 
 * @author saikumar
 *
 */
public interface StundentRepository extends JpaRepository<Student, Integer> {
}
