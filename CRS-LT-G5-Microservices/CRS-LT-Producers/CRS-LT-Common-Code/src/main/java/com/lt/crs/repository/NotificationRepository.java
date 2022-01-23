/**
 * 
 */
package com.lt.crs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lt.crs.model.Notification;

/**
 * @author saikumar
 *
 */
public interface NotificationRepository  extends JpaRepository<Notification, Integer>{

}
