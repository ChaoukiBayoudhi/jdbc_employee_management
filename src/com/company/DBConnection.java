package com.company;

import com.company.Exceptions.EmployeeNotFoundException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class DBConnection {
    private static String url="jdbc:postgresql://localhost:5432/companydb";
    private static String userName="companyuser";
    private static String password="companyuser";
    private static Connection conn;
   public static Connection getDbConnection()
   {

       try
       {
           conn = getConnection(url, userName, password);
           System.out.println("Connection has been established");
          // conn.close();

       }
       catch(SQLException e)
       {
           e.printStackTrace();
       }
       return conn;
   }

   public static void createTableEmployee()
   {
       try {
           //step 1 : establish connection
           Connection con=getDbConnection();
           //step 2-Write the sql request
           String request ="create table Employee ("+
                   "id bigint primary key,"+
                   "name varchar not null,"+
                   "birthdate date,"+
                   "salary real,"+
                   "hiredate date,"+
                   "mgr_id int null"+
                   ")";
           //step 3 : create Statement
           PreparedStatement ps=con.prepareStatement(request);
           //step 4 : Execute sql Query
           ps.executeUpdate();
           System.out.println("The Employee table has been successfully created");
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
   public static void closeConnection() throws SQLException {
       conn.close();
   }

   public static boolean insertEmployee(Employee emp) throws SQLException {

       try
       {
           Connection conn = getDbConnection();
           String req="insert into employee values(?,?,?,?,?,?)";
           PreparedStatement ps = conn.prepareStatement(req);
           ps.setLong(1,emp.getId());
           ps.setString(2,emp.getName());
           ps.setObject(3,emp.getBirthdate());
           ps.setBigDecimal(4,emp.getSalary());
           ps.setObject(5,emp.getHiredate());
           ps.setLong(6,emp.getManagerId());
           ps.executeUpdate();
           ps.close();
           return true;

       }catch(SQLException e) {
           System.out.println("Sql State = "+e.getSQLState()+"\nException Message = "+e.getMessage());
       }
       finally{
//           if(conn!=null)
//               conn.close();
           closeConnection();
      }
       return false;
   }
public static List<Employee> getAllEmployees()throws SQLException
{
    List<Employee> lstEmployees=new ArrayList<>();
    try{
        Connection con=getDbConnection();
        String request="select * from employee";
        PreparedStatement ps =con.prepareStatement(request);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            //create an employee
            Employee employee=new Employee();
            employee.setId(rs.getLong("id"));
            employee.setName(rs.getString("name"));
            employee.setBirthdate(rs.getObject("birthdate", LocalDate.class));
            employee.setHiredate(rs.getObject("hiredate", LocalDate.class));
            employee.setSalary(rs.getBigDecimal("salary"));
            employee.setManagerId(rs.getLong("mgr_id"));
            lstEmployees.add(employee);
        }

    }catch(SQLException e) {
        System.out.println("Sql State = "+e.getSQLState()+"\nException Message = "+e.getMessage());
    }
    finally{
        closeConnection();
    }
    return lstEmployees;
}
public static void deleteEmployee(long id)throws SQLException{
       try {
           Connection con=getDbConnection();
           String request="select id from employee where id=?";
           PreparedStatement ps =con.prepareStatement(request);
           ps.setLong(1,id);
           ResultSet rs = ps.executeQuery();

           boolean result=rs.next();
           if(!result)
               throw new EmployeeNotFoundException("There is not employee with id = "+id);
           ps.close();
           request="delete from employee where id ="+id;
           ps=con.prepareStatement(request);
           ps.executeUpdate();
           System.out.println("The employee with id = "+ id +" has been successfully deleted.");
           ps.close();
       }catch(SQLException e)
       {
           System.err.format("Sql State = "+e.getSQLState()+"\nException Message = "+e.getMessage());
       }
       catch(EmployeeNotFoundException e)
       {
           System.out.println(e.getMessage());
       }
       finally{
           closeConnection();
       }
}
public static void updateEmployee(Long id,Employee newEmp)throws SQLException
{
    try{
        Connection conn = getDbConnection();
        String request="select id from employee where id=?";
        PreparedStatement ps =conn.prepareStatement(request);
        ps.setLong(1,id);
        ResultSet rs = ps.executeQuery();

        boolean result=rs.next();
        if(!result)
            throw new EmployeeNotFoundException("There is not employee with id = "+id);
        ps.close();
        request="update employee set name=?, birthdate=?,hiredate=?,salary=?,mgr_id=?";
        ps =conn.prepareStatement(request);
        ps.setString(1,newEmp.getName());
        ps.setObject(2,newEmp.getBirthdate());
        ps.setObject(3,newEmp.getHiredate());
        ps.setBigDecimal(4,newEmp.getSalary());
        ps.setLong(5,newEmp.getManagerId());
        ps.executeUpdate();
        System.out.println("The Employee with id = "+ id +" has been successfully updated.");
    }
    catch(EmployeeNotFoundException e)
    {
        System.out.println(e.getMessage());
    }
       finally{
    closeConnection();
}
}

    public static Employee getEmployeeById(long id) throws SQLException {
        Employee eRes=new Employee();

        try {
            Connection conn = getDbConnection();
            String request = "select * from employee where id=?";
            PreparedStatement ps = conn.prepareStatement(request);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            boolean result = rs.next();
            if (!result)
                throw new EmployeeNotFoundException("There is not employee with id = " + id);
            eRes.setId(rs.getLong("id"));
            //...
            ps.close();
        }
            catch(EmployeeNotFoundException e)
            {
                System.out.println(e.getMessage());
            }
        catch(SQLException e)
        {
            System.err.format("Sql State = "+e.getSQLState()+"\nException Message = "+e.getMessage());
        }
       finally{
                closeConnection();
            }
        return eRes;
    }
}
