package com.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private int id;
    private String name;
    private LocalDate birthdate;
    private BigDecimal salary;
    private LocalDate hiredate;
    private int managerId;
    //private int deptNo;
}
