package com.company;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static java.lang.Character.toUpperCase;

public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner sc=new Scanner(System.in);
        int choice;
        do {
            System.out.println("Select a choice :");
            System.out.println("1-Create Employee Table");
            System.out.println("2-Add new Employee");
            System.out.println("3-Show All Employees");
            System.out.println("4-Delete an Employee");
            System.out.println("5-Update an Employee");
            System.out.println("6-Crate Photos Table");
            System.out.println("7-Add an Employee Photo");
            System.out.println("8-Delete an Employee Photo");

            System.out.println("9-Exit Application");
            do {
                System.out.println("your choice ? = ");
                choice = sc.nextInt();
            }while(choice<1||choice>9);

            switch(choice) {
                case 1:
                    DBConnection.createTableEmployee();
                    DBConnection.closeConnection();
                    break;
                case 2:
                    Employee e1 = new Employee();
                    e1.getEmployee();
                    DBConnection.insertEmployee(e1);
                    System.out.println("Employee with id = " + e1.getId() + " has been successfully added");
                    break;
                case 3:
                    List<Employee> lstres = DBConnection.getAllEmployees();
                    System.out.println("List Of Employees :");
                    for (Employee e : lstres)
                        System.out.println(e);
                    break;
                case 4 :
                    System.out.println("Id of the employee to be deleted = ");
                    long id=sc.nextLong();
                    DBConnection.deleteEmployee(id);
                    break;
                case 5:
                    Employee newEmp=new Employee();
                    System.out.println("Id of the employee to be deleted = ");
                    id=sc.nextLong();
                    System.out.println("modify name ?(y/n) ");
                    char modify=toUpperCase(sc.next().charAt(0));
                    if(modify=='y') {
                        System.out.println("new \"name\" value = ? ");
                        newEmp.setName(sc.nextLine());
                    }
                    System.out.println("modify salary ?(y/n) ");
                    modify=toUpperCase(sc.next().charAt(0));
                    if(modify=='y') {
                        System.out.println("new \"salary\" value = ? ");
                        newEmp.setSalary(sc.nextBigDecimal());
                    }
                    Employee e=DBConnection.getEmployeeById(id);
                    newEmp.setBirthdate(e.getBirthdate());
                    DBConnection.updateEmployee(id,newEmp);


            }
        }while(choice!=9);
    }
}
