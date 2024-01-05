package com.sabari.springBoot.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor  // Generate a no-args constructor
@RequiredArgsConstructor  // Generate a constructor for final/non-null fields
@AllArgsConstructor  // Generate a constructor for all fields
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer employeeId;

    @NonNull  // Mark as non-null for inclusion in RequiredArgsConstructor
    private String employeeName;

    private String employeeCity;
    private long employeeSalary;
}
