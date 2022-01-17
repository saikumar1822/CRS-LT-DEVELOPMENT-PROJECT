package com.lt.crs.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.lt.crs.model.Student;

/**
 * 
 * @author Diwakar,Sai,Abdul,Rohan,Siva
 *
 */

public class StudentMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Student s=new Student();
		s.setStudentID(rs.getInt(1));

		
		return s;
	}


}
