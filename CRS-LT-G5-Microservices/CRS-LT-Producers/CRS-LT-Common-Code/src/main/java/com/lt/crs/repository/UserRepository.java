package com.lt.crs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lt.crs.model.User;
/**
 * 
 * @author saikumar
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {

	/**
	 * @param userId
	 * @param password
	 * @return
	 */
	User findByIdAndPassword(int userId, String password);

}
