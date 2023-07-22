package com.nag.poc.controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.nag.poc.model.Employee;
import com.nag.poc.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)

public class EmloyeeControllerTest {
	
	@InjectMocks
	EmployeeController employeeController;
	
	@Mock
	EmployeeRepository employeeRepository;
	
	@Test
	public void testSaveEmployee() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		Employee emp = new  Employee("Naga", "Raju", "nagaraju@gamil.com");
		
		when(employeeRepository.save(any(Employee.class))).thenReturn(emp);
		
		Employee responseEntity = employeeController.saveEmployee(emp);
		
		assertTrue(responseEntity.getEmail().equals("nagaraju@gamil.com"));
	}

}
