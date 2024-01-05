package com.sabari.springBoot.controller;

import com.sabari.springBoot.DTO.EmployeeRequest;
import com.sabari.springBoot.DTO.EmployeeResponse;
import com.sabari.springBoot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public String createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        employeeService.addEmployee(employeeRequest);
        return "Employee created successfully";
    }

    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable Integer id, @RequestBody EmployeeRequest employeeRequest) {
        employeeService.updateEmployee(id, employeeRequest);
        return "Employee updated successfully";
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return "Employee deleted successfully";
    }
}
