package org.example;

import java.sql.*;
//import java.time.LocalDate;
import java.util.Scanner;

public class Class1
{
    public static void main(String[] args)
    {
        Select_Choice1 sc=new Select_Choice1();
        sc.select_choice1();
    }
}
class Select_Choice1
{
    String option=null;
    String choice=null;
    Scanner input=new Scanner(System.in);
    void select_choice1()
    {
        System.out.println("------Welcome to WIZE COMMERCE ATTENDENCE SYSTEM--------");
        System.out.println("A. If you want to see All Employee's Information. ");
        System.out.println("B. If you want to Add New Employee's Information. ");
        System.out.println("C. If you want to add Employee's Attendence. ");
        System.out.println("D. If you want to get Employee's Attendence.");
        System.out.println("E: If you want to delete an Employee's details");

        System.out.println("Select your Option :- A,B,C,D,E ");

        option=input.next();
        switch (option)
        {
            case "A":
                Employee_Info1 e1=new Employee_Info1();
                e1.show_employee_details();
                break;

            case "B":
                Employee_Info1 e2=new Employee_Info1();
                e2.enter_employee_details();
                break;

            case "C":
                Employee_Info1 e3=new Employee_Info1();
                e3.enter_employee_attendence();
                break;

            case "D":
                Employee_Info1 e4=new Employee_Info1();
                e4.show_employee_attendence_details();
                break;

            case "E":
                Employee_Info1 e5=new Employee_Info1();
                e5.delete_employee_details();
                break;



            default:
                break;
        }

        System.out.println("Do want to continue ? Y : N");
        choice=input.next();
        if (choice.equals("Y"))
        {
            select_choice1();
        }
    }
}

class Employee_Info1
{  Connection con=null;
    Statement st=null;
    PreparedStatement ps=null;
    ResultSet rs1=null;

    Scanner input2=new Scanner(System.in);
    int enteries;
    int sno;
    String first_name;
    String last_name;
    String dept_name;
    String dept_id;
    String dob;
    Timestamp doj;
    Time time;
    Date date;

    void show_employee_details()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Attendence","root","root");
            String query="select * from employee_info";
            ps=con.prepareStatement(query);
            rs1=ps.executeQuery();
            while(rs1.next())
            {
                sno=rs1.getInt("SNO");
                first_name=rs1.getString("FIRST_NAME");
                last_name=rs1.getString("LAST_NAME");
                dept_name=rs1.getString("DEPT_NAME");
                dept_id=rs1.getString("DEPT_ID");
                dob=rs1.getString("DOB");
                doj=rs1.getTimestamp("DOJ");

                System.out.println(sno+"   "+first_name+"   "+last_name+"   "+dept_name+"   "+dept_id+"   "+dob+"   "+doj);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

    void enter_employee_details()
    {       System.out.println("Enter the number of Enteries ");
        enteries=input2.nextInt();

        for(int i=1;i<=enteries;i++)
        {
            System.out.println("Enter the SNO");
            sno=input2.nextInt();

            System.out.println("Enter the FIRST_NAME");
            first_name=input2.next();

            System.out.println("Enter the LAST_NAME");
            last_name=input2.next();

            System.out.println("Enter the DEPT_NAME");
            dept_name=input2.next();

            System.out.println("Enter the DEPT_ID");
            dept_id=input2.next();

            System.out.println("Enter the DOB");
            dob=input2.next();

            System.out.println("DOJ is added automatically");
            doj=new Timestamp(System.currentTimeMillis());

            add_new_employee_info(sno,first_name,last_name,dept_name,dept_id,dob,doj);
        }

    }


    void add_new_employee_info(int sno,String first_name,String last_name,String dept_name,String dept_id,String dob,Timestamp doj)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");   //load the mysql driver//
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Attendence","root","root"); // Establish connection with mysql database
            String query="insert into employee_info (SNO,FIRST_NAME,LAST_NAME,DEPT_NAME,DEPT_ID,DOB,DOJ) values (?,?,?,?,?,?,?)";
            ps=con.prepareStatement(query);

            ps.setInt(1,sno);
            ps.setString(2,first_name);
            ps.setString(3,last_name);
            ps.setString(4,dept_name);
            ps.setString(5,dept_id);
            ps.setString(6,dob);
            ps.setTimestamp(7,doj);

            ps.executeUpdate();

        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.out.println(e);
        }

    }

    void show_employee_attendence_details()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Attendence","root","root");
            String query="select * from demo";
            ps=con.prepareStatement(query);
            rs1=ps.executeQuery();
            while(rs1.next())
            {

                dept_name=rs1.getString("DEPT_NAME");
                dept_id=rs1.getString("DEPT_ID");
                date= rs1.getDate("DATE");


                System.out.println(dept_name+"   "+dept_id+"   "+date);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

    void enter_employee_attendence()
    {

        System.out.println("Enter the DEPT_ID");
        dept_id=input2.next();

        System.out.println("Enter the DEPT_NAME");
        dept_name=input2.next();




        //date=Date.valueOf(LocalDate.now());
        System.out.println(date);
        System.out.println("local date is added automatically");

        add_employee_attendence_info(dept_name,dept_id,date);

    }

    void add_employee_attendence_info(String dept_name, String dept_id, Date date)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");   //load the mysql driver//
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Attendence","root","root"); // Establish connection with mysql database
            String query="select DEPT_ID,DEPT_NAME from employee_info where DEPT_ID=? && DEPT_NAME=?";//UNION select DEPT_ID,DEPT_NAME,DATE from demo where DEPT_ID=? && DEPT_NAME=? && DATE=?  ";
            ps=con.prepareStatement(query);

            ps.setString(1,dept_id);
            ps.setString(2,dept_name);
           // ps.setString(3,dept_id);
            //ps.setString(4,dept_name);
           // ps.setDate(3, date);

           rs1= ps.executeQuery();
            while(rs1.next())
            {
               try
            {
                Class.forName("com.mysql.jdbc.Driver");   //load the mysql driver//
                con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Attendence","root","root"); // Establish connection with mysql database
                String query1="insert into demo (DEPT_ID,DEPT_NAME,DATE) values (?,?,?)";//UNION select DEPT_ID,DEPT_NAME,DATE from demo where DEPT_ID=? && DEPT_NAME=? && DATE=?  ";
                ps=con.prepareStatement(query1);

                ps.setString(1,dept_id);
                ps.setString(2,dept_name);
                ps.setDate(3, date);

                int j=ps.executeUpdate();
                if(j>0)
                {
                    System.out.println("Attendence addded");
                }
                else
                {
                    System.out.println("You are not Authorised and Attendence not addded");
                }
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            }

        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.out.println(e);
        }
    }

    void delete_employee_details()
    {       System.out.println("Enter ID of employee you want to delete");
            dept_id=input2.next();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");   //load the mysql driver//
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Attendence","root","root"); // Establish connection with mysql database
            String query="DELETE demo.*, employee_info.* FROM demo INNER JOIN employee_info ON demo.dept_id = employee_info.dept_id WHERE (demo.dept_id)=?;";//UNION select DEPT_ID,DEPT_NAME,DATE from demo where DEPT_ID=? && DEPT_NAME=? && DATE=?  ";
            ps=con.prepareStatement(query);

            ps.setString(1,dept_id);
            ps.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.out.println(e);
        }
        
    }



}
