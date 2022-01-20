/**
 * 
 */
package com.lt.crs.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lt.crs.bean.Professor;
import com.lt.crs.bean.Student;


@Repository
public class ProfessorDAOImpl implements ProfessorDAOInterface{

	// Dummy database. Initialize with some dummy values.
	public static StudentDAOImpl s= new StudentDAOImpl();	
	public static List<Professor> prof;

	{
		prof = new ArrayList();
		prof.add(new Professor(1,"Amit","abcd","IT"));
		prof.add(new Professor(2,"Geetha","abcd","CSE"));
		prof.add(new Professor(3,"Madu","abcd","IT"));
		prof.add(new Professor(4,"Ravi","abcd","CSE"));
		prof.add(new Professor(5,"Pushpa","abcd","CSE"));
	
	}

	/**
	 * Returns list of Professors from dummy database.
	 * 
	 * @return list of Professors
	 */
	public List list() {
		return prof;
	}


	/**
	 * Return professor object for given id from dummy database. If professor is
	 * not found for id, returns null.
	 * 
	 * @param id
	 *            professor id
	 * @return professor object for given id
	 */
	public Professor get(int id) {

		for (Professor c : prof) {
			if (c.getProfessorId()==id) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Delete the professor object from dummy database. If professor not found for
	 * given id, returns null.
	 * 
	 * @param id
	 *            the professor id
	 * @return id of deleted professor object
	 */
	public String delete(int id) {

		for (Professor c : prof) {
			if (c.getProfessorId()==id) {
				prof.remove(c);
				return "deleted successfully";
			}
		}

		return null;
	}

	/**
	 * Update the professor object for given id in dummy database. If professor
	 * not exists, returns null
	 * 
	 * @param id
	 * @param professor
	 * @return professor object with id
	 */
	public Professor update(int id, Professor professor) {
		for (Professor c : prof) {
			if (c.getProfessorId()==id) {
				professor.setProfessorId(c.getProfessorId());
				prof.remove(c);
				prof.add(professor);
				return professor;
			}
		}

		return null;
	}

	public void insert(Professor prof1) {
		prof.add(prof1);

	}
	public Professor login(int id,String psw) {
		for (Professor c : prof) {
			if (c.getProfessorId()==id&& c.getProfessorPassword().equals(psw)) {
				return c;
			}
		}
		return null;
	}

	public Student addGrades(int id, int cid, char grade) {
		
		return null;
	}

	public  Student viewSpecificStudents(int cid) {
		//System.out.println(ci	d);

	
		return null;
	}
}
