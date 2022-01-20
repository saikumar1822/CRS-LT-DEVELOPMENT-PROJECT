
package com.lt.crs.controller;

import java.awt.PageAttributes.MediaType;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lt.crs.bean.Professor;
import com.lt.crs.service.ProfessorServiceInterface;



@RestController
@CrossOrigin
//@RequestMapping("/professorService")
public class ProfessorController {

	
	@Autowired
	private ProfessorServiceInterface professorDAO;
	
	
	//@GetMapping("/professors")
	@RequestMapping(method = RequestMethod.GET,value = "/professors")
	@ResponseBody
		public List getProfessors() {	
		
		return professorDAO.list();
	}
	
	//@GetMapping("/professors/{id}")
	@RequestMapping( method = RequestMethod.GET,value = "/professors/{id}")
	@ResponseBody
	public ResponseEntity getProfessors(@PathVariable("id") int id) {

		Professor Professor = professorDAO.get(id);
		if (Professor == null) {
			return new ResponseEntity("No Professor found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(Professor, HttpStatus.OK);
	}
	
	@RequestMapping( method = RequestMethod.GET,value = "/proflogin")
	@ResponseBody
	public ResponseEntity getProfessors(@RequestParam("id") int id,@RequestParam("pwd") String pwd) {

		Professor Professor = professorDAO.login(id, pwd);
		if (Professor == null) {
			return new ResponseEntity("No Professor found for ID " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(Professor, HttpStatus.OK);
	}

}
