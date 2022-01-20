package com.lt.crs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.crs.bean.Professor;
@Service
public class ProfessorService implements ProfessorServiceInterface{
	@Autowired com.lt.crs.dao.ProfessorDAOInterface profDao;
	@Override
	public List list() {
		// TODO Auto-generated method stub
		return profDao.list();
	}

	@Override
	public Professor get(int id) {
		// TODO Auto-generated method stub
		return profDao.get(id);
	}

	@Override
	public String delete(int id) {
		// TODO Auto-generated method stub
		return profDao.delete(id);
	}

	@Override
	public Professor update(int id, Professor professor) {
		// TODO Auto-generated method stub
		return profDao.update(id, professor);
	}

	@Override
	public void insert(Professor prof1) {
		// TODO Auto-generated method stub
		profDao.insert(prof1);
		
	}

	@Override
	public Professor login(int id, String psw) {
		// TODO Auto-generated method stub
		return profDao.login(id, psw);
	}

}
