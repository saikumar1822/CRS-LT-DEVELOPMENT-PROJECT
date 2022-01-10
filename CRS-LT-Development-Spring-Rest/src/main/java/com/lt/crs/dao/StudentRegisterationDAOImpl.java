package com.lt.crs.dao;




import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.lt.crs.constants.SqlQueryConstants;
import com.lt.crs.model.Student;



/**
 * 
 * @author sai kumar,Rohan,Diwakar,Abdul,Siva,Nikil
 * Student Registration related methods 
 * input name,password,department
 */

@Repository

public class StudentRegisterationDAOImpl implements StudentRegisterationDAOInterface{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	SimpleJdbcInsert simpleJdbcInsert;
	@Transactional
public String registerStudent(Student student) {
	try {
		simpleJdbcInsert=new SimpleJdbcInsert(jdbcTemplate);
		  simpleJdbcInsert.withTableName("student").usingGeneratedKeyColumns("student_id");
	  
		  //jdbcTemplate.update(SqlQueryConstants.STUDENT_REGISTERATION,student.getStudentName(),student.getStudentPassword(),student.getStudentDepartment());
		  MapSqlParameterSource params = new MapSqlParameterSource()
			        .addValue("student_name",student.getStudentName())
			        .addValue("student_password", student.getStudentPassword())
			        .addValue("student_department",student.getStudentDepartment());
		 Number id = simpleJdbcInsert.executeAndReturnKey(params);
		 return "Sccessfully added your user id is   student-"+id;
	}
	catch(Exception e) {
		throw new com.lt.crs.exception.IntegrityViolationException(e.getMessage());
	}
	
	
		
}
}
	
