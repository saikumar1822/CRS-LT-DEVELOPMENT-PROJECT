/**
 * 
 */
package com.lt.crs.service;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.crs.dto.LoginDto;
import com.lt.crs.dto.PasswordDto;
import com.lt.crs.dto.StudentDto;
import com.lt.crs.exception.StudentNotFoundException;
import com.lt.crs.exception.UserNotFoundException;
import com.lt.crs.model.Role;
import com.lt.crs.model.Student;
import com.lt.crs.model.User;
import com.lt.crs.repository.ProfessorRepository;
import com.lt.crs.repository.StundentRepository;
import com.lt.crs.repository.UserRepository;

/**
 * @author saikumar
 *UserServiceImpl class includes login, registration and update password functionalities
 */
@Service
public class UserServiceImpl implements UserService {
	public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	/**
	 * Injecting the required dependices through autowire annotation
	 */
	@Autowired
	StundentRepository studentRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProfessorRepository professorRepository;
	Student student=null;
/**
 * Student registration method saves the student details into student table if all the details are valid
 */
	@Override
	public Student saveStudent(StudentDto studentDto) {
		logger.info("inside save student method");
		if (studentDto == null || studentDto.getStudentName() == null || studentDto.getPassword() == null) {
			logger.error("Please fill all the required details!!"+studentDto);
			throw new StudentNotFoundException("Please fill all the required details!! ");
		}
		student=new Student();
		student.setStudentName(studentDto.getStudentName());
		student.setApproved(false);
		student.setPassword(studentDto.getPassword());
		student.setStudentDepartment(studentDto.getStudentDepartment());
		logger.info("end of save student method");
		return studentRepository.save(student);
	}
/**
 * Login impl method checks user id and password if matches login successful else throws exception
 */
	@Override
	public User userLogin(LoginDto loginDto) {
		logger.info("inside user login method");
		if (loginDto == null || loginDto.getPassword() == null || loginDto.getUserId() == 0) {
			logger.error(loginDto+" "+"please enter the valid details");
			throw new UserNotFoundException("Please enter the valid details!!");
		}	
		User user = userRepository.findByIdAndPassword(loginDto.getUserId(), loginDto.getPassword());
		// Set<Role> set = new HashSet<>(user.getRoles());
		

		if (user == null) {
			logger.error("Login unsucessful");
			throw new UserNotFoundException("Login unsucessful ! Please try A again");
		}
		Iterator<Role> iterator=user.getRoles().iterator();
		Role role=new Role();
		while(iterator.hasNext()) {
			role=iterator.next();
		}
		if(role.getId()==2) {
			Student student=studentRepository.findById(user.getId()).get();
			if(!student.isApproved()) {
				logger.error("approval is pending with admin");
				throw new UserNotFoundException("Dear user your approval is pending / Rejected by the Admin. Please contact the admin");
			}
		}
		logger.info("end of user login method");
		return user;
	}
/**
 * Update password method
 */
	@Override
	public User chnagePassword(PasswordDto passwordDto) {
		logger.info("inside update password method");
		if (passwordDto == null || passwordDto.getOldPassword() == null || passwordDto.getUserId() == 0) {
			logger.error("please enter all the fileds"+passwordDto);
			throw new UserNotFoundException("Please enter the valid details!!");
		}
		User user = userRepository.findByIdAndPassword(passwordDto.getUserId(), passwordDto.getOldPassword());
		logger.info("user details :"+user);
		if (user == null) {
			logger.error("details are not matching please try again");
			throw new UserNotFoundException("Details are not matching ! Please try again");
		}
		user.setPassword(passwordDto.getNewPassword());

		// finding the user role
		Iterator<Role> iterator = user.getRoles().iterator();
		Role role = new Role();
		while (iterator.hasNext()) {
			role = iterator.next();
		}

		if (role.getName().equalsIgnoreCase("student")) {

			Student student = studentRepository.findById(user.getId()).orElseThrow(() -> new StudentNotFoundException(
					" student is not found with this id" + " " + passwordDto.getUserId()));
			student.setPassword(passwordDto.getNewPassword());
			studentRepository.save(student);
		}
		userRepository.save(user);
		logger.info("end of change password method");
		return user;
	}

}
