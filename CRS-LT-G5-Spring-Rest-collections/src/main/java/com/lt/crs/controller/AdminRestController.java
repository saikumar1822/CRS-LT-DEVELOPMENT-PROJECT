/**
 * 
 */
package com.lt.crs.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lt.crs.dao.*;
import com.lt.crs.bean.Professor;

/**
 * @author deepikareddy
 *
 */

@RestController
@CrossOrigin
public class AdminRestController {

	@Autowired
	private ProfessorDAOImpl professorDAO;

	//@PostMapping(value = "/post/professors")
	@RequestMapping( method = RequestMethod.POST,value = "/post/professors")
	@ResponseBody
	public ResponseEntity createProfessor(@RequestBody Professor Professor) {

		professorDAO.insert(Professor);

		return new ResponseEntity(Professor, HttpStatus.OK);
	}


	//@DeleteMapping("/delete/professors/{id}")
	@RequestMapping( method = RequestMethod.DELETE,value = "/delete/professors/{id}")
	@ResponseBody
	public ResponseEntity deleteProfessor(@PathVariable int id) {

		if (null == professorDAO.delete(id)) {
			return new ResponseEntity("No Professor found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(id, HttpStatus.OK);

	}

	//@PutMapping("/put/professors/{id}")
	@RequestMapping(method = RequestMethod.PUT,value = "/put/professors/{id}")
	@ResponseBody
	public ResponseEntity updateProfessor(@PathVariable int id, @RequestBody Professor Professor) {

		Professor = professorDAO.update(id, Professor);

		if (null == Professor) {
			return new ResponseEntity("No Professor found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(Professor, HttpStatus.OK);
	}

}
