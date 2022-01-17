package com.lt.crs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lt.crs.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	/**
	 * @param courseId
	 * @param studentId
	 */
	Payment findByCourseIdAndStudentId(int courseId, int studentId);

}
