package com.lt.crs.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.lt.crs.model.StudentGrade;
/**
 * 
 * @author saikumar
 *
 */
@RestController
public class ProfessorConsumerClient {
	@Autowired
	private DiscoveryClient discoveryClient;
/**
 * This is method is used to view the enrolled students for a particular professor
 * @param professorId
 * @return
 */
	@RequestMapping(method = RequestMethod.GET, value = "/EnrolledStudents")
	public ResponseEntity<String> viewEnrolledCourses(@RequestParam int professorId) {
		String professorId1 = "" + professorId;
		List<ServiceInstance> instances = discoveryClient.getInstances("professor-producer");
		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/EnrolledStudents";

		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		params.put("professorId", professorId1);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl);
		for (Map.Entry<String, String> entry : params.entrySet()) {
			builder.queryParam(entry.getKey(), entry.getValue());
		}
		String msg = restTemplate.getForObject(builder.toUriString(), String.class);

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	/**
	 * This add grade method adds grade for student 
	 * @param studentGrade
	 * @return
	 */
	@RequestMapping( method = RequestMethod.POST,value = "/grade")
	public ResponseEntity<String > addGrade(@RequestBody StudentGrade studentGrade) {
		List<ServiceInstance> instances = discoveryClient.getInstances("professor-producer");
		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/grade";
		RestTemplate restTemplate = new RestTemplate();

		String msg=restTemplate.postForObject(baseUrl, studentGrade, String.class);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}

	
	
	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
}
