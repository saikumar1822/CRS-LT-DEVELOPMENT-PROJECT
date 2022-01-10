package com.lt.crs.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.lt.crs.model.Student_Course;


/**
 * 
 * @author Sai
 *
 */
public class ListCourseWithLessThanThreeStudentsMapper implements RowMapper<Integer>  {

	@Override
	public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		com.lt.crs.model.Student_Course sc=new Student_Course();
		int i=rs.getInt(1);
		
		return new Integer(i);
	}


}
