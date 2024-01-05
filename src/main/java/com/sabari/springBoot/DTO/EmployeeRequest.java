package com.sabari.springBoot.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    private String employeeName;
    private long employeeSalary;
    private String employeeCity;

}
