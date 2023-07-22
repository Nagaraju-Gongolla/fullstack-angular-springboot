package com.nag.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nag.poc.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
