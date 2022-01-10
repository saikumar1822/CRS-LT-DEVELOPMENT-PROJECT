package com.lt.crs.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.lt.crs.model.Professor;

/**
 * 
 * @author Diwakar
 *
 */
public class ProfessorMapper implements RowMapper<Professor>{

	@Override
	public Professor mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Professor p=new Professor();
		p.setProfessorId(rs.getInt(1));
		return p;
	}

}
