package com.paulkiran.employee_springboot_jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulkiran.employee_springboot_jpa.model.Employee;
import com.paulkiran.employee_springboot_jpa.repository.EmployeeRepository;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    public String create(Employee e){
        employeeRepository.save(e);
        return (e.toString()+" Employee Added Successfully");
    }

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    public String delete(int id){
        Optional<Employee> opt = employeeRepository.findById(id);
        if(opt.isPresent()){
            employeeRepository.deleteById(id);
            return "Employee Deleted Sucessfully";
        }
        return "Emp Id not found";
    }

    public String update(Employee e){
        Optional<Employee> opt = employeeRepository.findById(e.getId());
        if(opt.isPresent()){
            employeeRepository.save(e);
            return "Employee Updated Sucessfully";
        }
        return "Employee Not Exists";
    }
}
