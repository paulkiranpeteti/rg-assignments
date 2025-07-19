package com.paulkiran.employee_springboot_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paulkiran.employee_springboot_jpa.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    
}
