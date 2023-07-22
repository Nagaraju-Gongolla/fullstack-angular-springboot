package com.nag.poc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nag.poc.exception.ResourceNotFoundException;
import com.nag.poc.model.Employee;
import com.nag.poc.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = { "*" }, maxAge = 3600) // this annotation is mandatory if the client code (angular) not fetching the data from service
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	// create employee rest api
	@PostMapping("/employees")
	public Employee  saveEmployee(@RequestBody Employee emp) {
		return employeeRepository.save(emp);
	}
	
	// get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		
		return employeeRepository.findAll();
		
	}
	
	// get employee by id rest api
	@GetMapping("/employees/{id}")
	public  ResponseEntity<Employee> getEmployeeByName(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id " + id));
		return ResponseEntity.ok(employee);
	
	}
	
	// update employee rest api
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id " + id));
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmail(employeeDetails.getEmail());
		Employee e = employeeRepository.save(employee);
		return ResponseEntity.ok(e);
		
	}
	
	// delete employee rest api
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id " + id));
		
		employeeRepository.delete(employee);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping("/")
	public String home() {
		return "<h1> Welcome </h1>";
	}
	@GetMapping("/user")
	public String user() {
		return "<h1> Hi! User </h1>";
	}
	@GetMapping("/admin")
	public String admin() {
		return "<h1> Hi! Admin </h1>";
	}
	
}
