package com.lt.crs.service;

import java.util.List;

import com.lt.crs.bean.Professor;

public interface ProfessorServiceInterface {
	public List list();
	public Professor get(int id);
	//public Professor create(Professor prof1);
	public String delete(int id);
	public Professor update(int id, Professor professor);
	public void insert(Professor prof1);
	public Professor login(int id,String psw) ;
}
