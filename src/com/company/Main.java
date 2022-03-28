package com.company;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner sc=new Scanner(System.in);
        System.out.println("Select a choice");
        int choice=sc.nextInt();
        switch(choice)
        {
            case 1:
                DBConnection.createTableEmployee();
                DBConnection.closeConnection();
break;

        }
    }
}
