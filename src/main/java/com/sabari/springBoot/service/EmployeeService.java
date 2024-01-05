package com.sabari.springBoot.service;

import com.sabari.springBoot.DTO.EmployeeRequest;
import com.sabari.springBoot.DTO.EmployeeResponse;
import com.sabari.springBoot.model.Employee;
import com.sabari.springBoot.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    // Create operation
    public void addEmployee(EmployeeRequest employeeRequest) {
        if (employeeRequest == null) {
            throw new IllegalArgumentException("EmployeeRequest cannot be null");
        }

        try {
            Employee employee = new Employee();
            employee.setEmployeeName(employeeRequest.getEmployeeName());
            employee.setEmployeeCity(employeeRequest.getEmployeeCity());
            employee.setEmployeeSalary(employeeRequest.getEmployeeSalary());

            employeeRepository.save(employee);
            log.info("Added employee {}", employee.getEmployeeName());
        } catch (Exception e) {
            log.error("Error occurred while adding employee", e);
            throw new RuntimeException("Error occurred while adding employee", e);
        }
    }

    // Read operation
    public EmployeeResponse getEmployeeById(Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElse(null);
        return mapEmployeeToEmployeeResponse(employee);
    }

    // Update operation
    public void updateEmployee(Integer employeeId, EmployeeRequest employeeRequest) {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

        existingEmployee.setEmployeeName(employeeRequest.getEmployeeName());
        existingEmployee.setEmployeeCity(employeeRequest.getEmployeeCity());
        existingEmployee.setEmployeeSalary(employeeRequest.getEmployeeSalary());

        employeeRepository.save(existingEmployee);
        log.info("Updated employee with id {}: {}", employeeId, existingEmployee.getEmployeeName());
    }

    // Delete operation
    public void deleteEmployee(Integer employeeId) {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

        employeeRepository.delete(existingEmployee);
        log.info("Deleted employee with id {}: {}", employeeId, existingEmployee.getEmployeeName());
    }

    // List all employees
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return mapEmployeeListToEmployeeResponseList(employees);
    }

    private List<EmployeeResponse> mapEmployeeListToEmployeeResponseList(List<Employee> employees) {
        return employees.stream()
                .map(employee -> mapEmployeeToEmployeeResponse(employee))
                .collect(Collectors.toList());
    }

    private EmployeeResponse mapEmployeeToEmployeeResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getEmployeeId(),
                employee.getEmployeeName(),
                employee.getEmployeeCity(),
                employee.getEmployeeSalary()
        );
    }
}
