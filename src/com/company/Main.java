package com.company;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner sc=new Scanner(System.in);
        int choice;
        System.out.println("Select a choice :");
        System.out.println("1-Create Employee Table");
        System.out.println("2-Add new Employee");
        System.out.println("3-Exit Application");
        do {
            choice = sc.nextInt();


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
        }
        }while(choice!=3);
    }
}
