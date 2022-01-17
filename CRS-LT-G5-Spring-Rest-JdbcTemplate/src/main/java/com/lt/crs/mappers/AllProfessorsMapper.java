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
public class AllProfessorsMapper implements RowMapper<Professor>{

	@Override
	public Professor mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Professor p=new Professor();
		p.setProfessorId(rs.getInt(1));
		p.setProfessorName(rs.getString(2));
		p.setProfessorDepartment(rs.getString(4));
		return p;
	}

}
