package com.paulkiran.employee_springboot_jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.paulkiran.employee_springboot_jpa.model.Employee;
import com.paulkiran.employee_springboot_jpa.service.EmployeeService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("add")
    public String addEmployee(@RequestBody Employee e) {
        return employeeService.create(e);
    }

    @GetMapping("getall")
    public List<Employee> getEmployees(){
        return employeeService.getAll();
    }

    @PutMapping("update")
    public String updateEmployee(@RequestBody Employee e) {
        return updateEmployee(e);
    }
    
    @DeleteMapping("delete/{id}")
    public String deleteEmployee(@PathVariable int id){
        return employeeService.delete(id);
    }
    
}
