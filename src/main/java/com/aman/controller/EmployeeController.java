package com.aman.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import com.aman.entity.Employee;
import com.aman.repository.emprepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

public class EmployeeController {

	@Autowired
	private emprepository repo;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {

		return repo.findAll();
	}

	@PostMapping("/employees")
	public Employee insertemp(@RequestBody Employee emp) {
		return repo.save(emp);
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getempbyid(@PathVariable int id) {
		Employee emp = repo.findById(id).orElseThrow(
				(() -> new com.aman.exception.ResourceNotFoundException("Employee not exists with id:" + id)));

		return ResponseEntity.ok(emp);
	}

	

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateemp(@PathVariable int id, @RequestBody Employee employeedetails) {
	    Employee employee = repo.findById(id).orElseThrow(() -> new com.aman.exception.ResourceNotFoundException("Employee not exists with id:" + id));
	    
	    employee.setFirstname(employeedetails.getFirstname());
	    employee.setLastname(employeedetails.getLastname());
	    employee.setSalary(employeedetails.getSalary());
	    
	    // Save the updated employee to the database
	    employee = repo.save(employee);

	    return ResponseEntity.ok(employee);
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteemp(@PathVariable int id) {
		
		
		    Employee employee = repo.findById(id).orElseThrow(() -> new com.aman.exception.ResourceNotFoundException("Employee not exists with id:" + id));
		repo.delete(employee);
		
		Map<String, Boolean>response=new HashMap<String,Boolean>();
		
		response.put("deleted", Boolean.TRUE);
		
		return ResponseEntity.ok(response);
	}

}



