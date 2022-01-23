package com.lt.crs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lt.crs.dto.LoginDto;
import com.lt.crs.dto.PasswordDto;
import com.lt.crs.dto.StudentDto;

@RestController
public class UserControllerClient {
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	/**
	 * student can self register himself by using this method
	 * @param studentDto
	 * @return
	 */
	@RequestMapping( method = RequestMethod.POST,value = "/register")
	public ResponseEntity<String> saveStudent(@RequestBody StudentDto studentDto) {
		List<ServiceInstance> instances = discoveryClient.getInstances("user-producer");
		ServiceInstance serviceInstance = instances.get(0);
		
		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/register";
		RestTemplate restTemplate = new RestTemplate();
		String msg=restTemplate.postForObject(baseUrl, studentDto, String.class);

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	/***
	 * Login method 
	 * @param loginDto
	 * @return
	 */
	
	@RequestMapping( method = RequestMethod.POST,value = "/login")
	public ResponseEntity<String> userLogin(@RequestBody LoginDto loginDto) {
		List<ServiceInstance> instances = discoveryClient.getInstances("user-producer");
		ServiceInstance serviceInstance = instances.get(0);
		
		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/login";
		RestTemplate restTemplate = new RestTemplate();
		String msg=restTemplate.postForObject(baseUrl, loginDto, String.class);

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	/**
	 * Update password rest endpoint
	 * @param passwordDto
	 * @return
	 */
	@RequestMapping( method = RequestMethod.POST,value = "/updatePassword")
	public ResponseEntity<String> chnagePassword(@RequestBody PasswordDto passwordDto) {
		List<ServiceInstance> instances = discoveryClient.getInstances("user-producer");
		ServiceInstance serviceInstance = instances.get(0);
		
		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/updatePassword";
		RestTemplate restTemplate = new RestTemplate();
		String msg=restTemplate.postForObject(baseUrl, passwordDto, String.class);

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	


}
