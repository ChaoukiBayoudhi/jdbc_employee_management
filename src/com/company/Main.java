package com.company;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner sc=new Scanner(System.in);
        int choice;
        do {
            System.out.println("Select a choice :");
            System.out.println("1-Create Employee Table");
            System.out.println("2-Add new Employee");
            System.out.println("3-Show All Employees");
            System.out.println("4-Exit Application");
            do {
                System.out.println("your choice ? = ");
                choice = sc.nextInt();
            }while(choice<1||choice>4);

            switch(choice)
            {
                case 1:
                    DBConnection.createTableEmployee();
                    DBConnection.closeConnection();
                break;
                case 2:
                    Employee e1=new Employee();
                    e1.getEmployee();
                    DBConnection.insertEmployee(e1);
                    System.out.println("Employee with id = "+e1.getId()+" has been successfully added");
                    break;
                case 3:
                    List<Employee> lstres=DBConnection.getAllEmployees();
                    System.out.println("List Of Employees :");
                    for(Employee e : lstres)
                        System.out.println(e);
        }           break;
        }while(choice!=3);
    }
}
