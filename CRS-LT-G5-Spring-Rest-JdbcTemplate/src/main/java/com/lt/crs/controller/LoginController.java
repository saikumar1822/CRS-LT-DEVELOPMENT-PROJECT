package com.lt.crs.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lt.crs.model.Student;
import com.lt.crs.model.User;
import com.lt.crs.service.LoginUserInterface;


/**
 * 
 * @author Diwakar,Siva
 * This is Login Controller class 
 */ 

@RestController
public class LoginController {
	
	@Autowired
	LoginUserInterface loginser;
	
	@RequestMapping(method=RequestMethod.PUT,value="/pwd-updation")
	public ResponseEntity<String> studentRegistration(@RequestBody User user,@RequestParam String userId,@RequestParam String newpw){
		String res=loginser.updatePassword(userId, user.getUserPassword(), newpw);
		return new ResponseEntity<String>(res, HttpStatus.OK);	
	}
	
	
	@RequestMapping(method=RequestMethod.GET,value="/user-login")
	public ResponseEntity<Integer> payment(@RequestParam String userId,@RequestParam String pwd) {
	int  res=loginser.userLogin(userId, pwd);
		return new ResponseEntity<Integer>(res, HttpStatus.OK);
	}	

}
