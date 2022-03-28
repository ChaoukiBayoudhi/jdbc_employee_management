package com.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

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
    //get student's attributes values from the keyboard
    public void getEmployee()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("id ? = ");
        this.id=sc.nextInt();
        System.out.println("name ? = ");
        sc.nextLine();
        this.name=sc.nextLine();
        System.out.println("birthdate ? = ");
        String birthday=sc.next();
        //declare date format
        String dateFormat="yyyy-MM-dd";
        DateTimeFormatter format=DateTimeFormatter.ofPattern(dateFormat);
        //convert date got as String to LocalDate using Format
        this.birthdate=LocalDate.parse(birthday,format);
        System.out.println("hiredate ? = ");
        String hiredate=sc.next();
        this.hiredate=LocalDate.parse(hiredate,format);
        System.out.println("salary ? = ");
        this.salary=sc.nextBigDecimal();
        System.out.println("manager id ? = ");
        this.managerId=sc.nextInt();
    }
}
