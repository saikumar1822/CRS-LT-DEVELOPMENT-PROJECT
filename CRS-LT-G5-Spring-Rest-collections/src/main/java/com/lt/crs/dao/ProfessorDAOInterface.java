/**
 * 
 */
package com.lt.crs.dao;

import java.util.List;

import com.lt.crs.bean.Professor;



public interface ProfessorDAOInterface {

	public List list();
	public Professor get(int id);
	public String delete(int id);
	public Professor update(int id, Professor professor);
	public void insert(Professor prof1);
	public Professor login(int id,String psw) ;
}
