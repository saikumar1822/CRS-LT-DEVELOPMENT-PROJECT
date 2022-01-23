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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.lt.crs.dto.PaymentDto;
import com.lt.crs.model.StudentCourse;

/**
 * 
 * @author saikumar
 * 
 *         StudentConsumerClient is class communicates with student producers
 *         service and consumes
 *
 */
@RestController
public class StudentConsumerClient {

	@Autowired
	private DiscoveryClient discoveryClient;

	/**
	 * get courses method is used for showing the list of available courses in the
	 * databases
	 * 
	 * @return
	 * @throws RestClientException
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/courses")
	public ResponseEntity<String> getCourses() throws RestClientException, IOException {
		List<ServiceInstance> instances = discoveryClient.getInstances("student-producer");
		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/courses";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return new ResponseEntity<String>(response.getBody(), HttpStatus.OK);
	}

	/**
	 * DropCourse method is used to drop the course by the student
	 * 
	 * @param studentId
	 * @param courseId
	 * @return
	 */

	@RequestMapping(method = RequestMethod.DELETE, value = "/dropCourse/{studentId}/{courseId}")
	public ResponseEntity<String> dropCourse(@PathVariable int studentId, @PathVariable int courseId) {

		List<ServiceInstance> instances = discoveryClient.getInstances("student-producer");
		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/dropCourse/" + studentId + "/" + courseId;

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(baseUrl);

		return new ResponseEntity<String>("SuccessFully Deleted", HttpStatus.OK);
	}

	/**
	 * Add course method is used for student can able register for course
	 * 
	 * @param student
	 * @return
	 */

	@RequestMapping(method = RequestMethod.POST, value = "/addCourse")
	public ResponseEntity<String> saveCourse(@RequestBody StudentCourse student) {
		List<ServiceInstance> instances = discoveryClient.getInstances("student-producer");
		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/addCourse";
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.postForObject(baseUrl, student, String.class);

		return new ResponseEntity<String>("SuccessFully Added", HttpStatus.OK);
	}

	/**
	 * Payment method , once user is done with the course registration student can
	 * make the payment
	 * 
	 * @param paymentDto
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/payment")
	public ResponseEntity<String> payment(@RequestBody PaymentDto paymentDto) {
		List<ServiceInstance> instances = discoveryClient.getInstances("student-producer");
		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/payment";

		RestTemplate restTemplate = new RestTemplate();

		String msg = restTemplate.postForObject(baseUrl, paymentDto, String.class);

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	/**
	 * Once admin generates the report card student can able to view the report card
	 * 
	 * @param studentId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/reportCard")
	public ResponseEntity<String> viewReportCard(@RequestParam int studentId) {
		String studentId1 = "" + studentId;
		List<ServiceInstance> instances = discoveryClient.getInstances("student-producer");
		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/reportCard";

		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		params.put("studentId", studentId1);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl);
		for (Map.Entry<String, String> entry : params.entrySet()) {
			builder.queryParam(entry.getKey(), entry.getValue());
		}
		String msg = restTemplate.getForObject(builder.toUriString(), String.class);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	/**
	 * Student can pass his id and view the list of courses that he enrolled for
	 * 
	 * @param studentId
	 * @return
	 */

	@RequestMapping(method = RequestMethod.GET, value = "/EnrolledCourses")
	public ResponseEntity<String> viewEnrolledCourses(@RequestParam int studentId) {
		String studentId1 = "" + studentId;
		List<ServiceInstance> instances = discoveryClient.getInstances("student-producer");
		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/EnrolledCourses";

		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		params.put("studentId", studentId1);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl);
		for (Map.Entry<String, String> entry : params.entrySet()) {
			builder.queryParam(entry.getKey(), entry.getValue());
		}
		String msg = restTemplate.getForObject(builder.toUriString(), String.class);

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}

}
