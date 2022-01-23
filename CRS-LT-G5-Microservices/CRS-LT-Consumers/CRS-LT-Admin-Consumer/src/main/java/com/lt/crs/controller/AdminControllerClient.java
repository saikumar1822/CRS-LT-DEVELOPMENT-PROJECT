package com.lt.crs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.lt.crs.dto.ProfessorDto;
import com.lt.crs.model.Course;

/**
 * 
 * @author saikumar
 * 
 *         {@link AdminControllerClient} includes all the admin functionalities
 *         which then connects with admin producer service
 */
@RestController
public class AdminControllerClient {
	/**
	 * Injecting {@link DiscoveryClient} throw @autowired annotation
	 */

	@Autowired
	private DiscoveryClient discoveryClient;

	/**
	 * Add course method in AdminControllerClient connects with admin producer
	 * service and saves the course info into db by the admin
	 * 
	 * @param course
	 * @return String
	 * @throws HttpClientErrorException,{@link ResourceAccessException},
	 *                                         {@link RuntimeException}
	 */

	@RequestMapping(method = RequestMethod.POST, value = "/course")
	public ResponseEntity<String> saveCourse(@RequestBody Course course) {
		List<ServiceInstance> instances = discoveryClient.getInstances("admin-producer");
		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/course";
		RestTemplate restTemplate = new RestTemplate();
		String msg = null;

		try {
			msg = restTemplate.postForObject(baseUrl, course, String.class);
		} catch (HttpClientErrorException e) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, e.getMessage());
		} catch (ResourceAccessException e) {
			throw new ResourceAccessException("Connection not found please check weather producer is up or not ");
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong please check");
		}

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	/**
	 * This method is used for to communicate with the admin producer sevice for
	 * deleting the course by id
	 * 
	 * @param id
	 * @return String
	 * @throws HttpClientErrorException, ResourceAccessException
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/course/{id}")
	public ResponseEntity<String> deleteCourseById(@PathVariable int id) {
		List<ServiceInstance> instances = discoveryClient.getInstances("admin-producer");
		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/course/" + id;
		RestTemplate restTemplate = new RestTemplate();

		try {
			restTemplate.delete(baseUrl);
		} catch (HttpClientErrorException e) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, e.getMessage());
		} catch (ResourceAccessException e) {
			throw new ResourceAccessException("Connection not found please check weather producer is up or not ");
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong please check");
		}
		return new ResponseEntity<String>("Sucessfully deleted the course id: " + id, HttpStatus.OK);
	}

	/**
	 * This method is used for to communicate with the admin producer sevice for
	 * saving the professor info into data base by the admin
	 * 
	 * @param professorDto
	 * @return string
	 * @throws HttpClientErrorException, ResourceAccessException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/professor")
	public ResponseEntity<String> saveProfessor(@RequestBody ProfessorDto professorDto) {
		List<ServiceInstance> instances = discoveryClient.getInstances("admin-producer");
		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/professor";
		RestTemplate restTemplate = new RestTemplate();

		// String msg = restTemplate.postForObject(baseUrl, professorDto, String.class);
		String msg = null;

		try {
			msg = restTemplate.postForObject(baseUrl, professorDto, String.class);
		} catch (HttpClientErrorException e) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, e.getMessage());
		} catch (ResourceAccessException e) {
			throw new ResourceAccessException("Connection not found please check weather producer is up or not ");
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong please check");
		}

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	/**
	 * This method is used for to communicate with the admin producer service for
	 * Removing the professor by the admin
	 * 
	 * @param professorId
	 * @return
	 */

	@RequestMapping(method = RequestMethod.DELETE, value = "/professor/{professorId}")
	public ResponseEntity<String> deleteProfessorById(@PathVariable int professorId) {
		List<ServiceInstance> instances = discoveryClient.getInstances("admin-producer");
		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/professor/" + professorId;
		RestTemplate restTemplate = new RestTemplate();

		try {
			restTemplate.delete(baseUrl);
		} catch (HttpClientErrorException e) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, e.getMessage());
		} catch (ResourceAccessException e) {
			throw new ResourceAccessException("Connection not found please check weather producer is up or not ");
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong please check");
		}
		return new ResponseEntity<String>("Sucessfully deleted the Id" + professorId, HttpStatus.OK);
	}

	/**
	 * This method is used for to communicate with the admin producer service for
	 * approving the student by the admin
	 * 
	 * @param studentId
	 * @return
	 * @throws HttpClientErrorException,{@link ResourceAccessException}
	 */

	@RequestMapping(method = RequestMethod.GET, value = "/approveStudent")
	public ResponseEntity<String> approveStudent(@RequestParam int studentId) {
		String studentId1 = "" + studentId;
		List<ServiceInstance> instances = discoveryClient.getInstances("admin-producer");
		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/approveStudent";

		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		params.put("studentId", studentId1);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl);
		for (Map.Entry<String, String> entry : params.entrySet()) {
			builder.queryParam(entry.getKey(), entry.getValue());
		}
		String msg = null;
		try {
			msg = restTemplate.getForObject(builder.toUriString(), String.class);
		} catch (HttpClientErrorException e) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, e.getMessage());
		} catch (ResourceAccessException e) {
			throw new ResourceAccessException("Connection not found please check weather producer is up or not ");
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong please check");
		}

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	/**
	 * This method is used to reject the student registration ,internally it
	 * connects to admin producer and rejects the student registration by the admin.
	 * 
	 * @param studentId
	 * @return string
	 * @throws HttpClientErrorException,{@link ResourceAccessException}
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/rejectStudent/{studentId}")
	public ResponseEntity<String> rejectStudent(@PathVariable int studentId) {
		List<ServiceInstance> instances = discoveryClient.getInstances("admin-producer");
		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/rejectStudent/" + studentId;

		RestTemplate restTemplate = new RestTemplate();

		restTemplate.delete(baseUrl);

		try {
			restTemplate.delete(baseUrl);
		} catch (HttpClientErrorException e) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, e.getMessage());
		} catch (ResourceAccessException e) {
			throw new ResourceAccessException("Connection not found please check weather producer is up or not ");
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong please check");
		}

		return new ResponseEntity<String>("sucessfully rejected the student id:" + studentId, HttpStatus.OK);
	}

	/**
	 * Generate report card method
	 * 
	 * @return string
	 * @throws HttpClientErrorException,{@link ResourceAccessException}
	 */

	@RequestMapping(method = RequestMethod.GET, value = "/generateReportCard")
	public ResponseEntity<String> generateReportCard() {
		List<ServiceInstance> instances = discoveryClient.getInstances("admin-producer");
		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/generateReportCard";

		RestTemplate restTemplate = new RestTemplate();
		String result = null;

		try {
			result = restTemplate.getForObject(baseUrl, String.class);
		} catch (HttpClientErrorException e) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, e.getMessage());
		} catch (ResourceAccessException e) {
			throw new ResourceAccessException("Connection not found please check weather producer is up or not ");
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong please check");
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

}
