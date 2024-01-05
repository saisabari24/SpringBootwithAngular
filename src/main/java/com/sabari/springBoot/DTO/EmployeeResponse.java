package com.sabari.springBoot.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private Integer employeeId;
    private String employeeName;
    private String employeeCity;
    private long employeeSalary;

}
