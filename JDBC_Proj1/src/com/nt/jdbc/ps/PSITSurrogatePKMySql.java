package com.nt.jdbc.ps;
/*JDBC app that inserts values into Student table using PreparedStatement and using auto increment constraint for PK column*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PSITSurrogatePKMySql {
	private static final String STUDENT_INSERT_QUERY="INSERT INTO STUDENT(SNAME,SADD,AVG) VALUES(?,?,?)";
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			//read inputs
			sc = new Scanner(System.in);
			int count = 0;
			if(sc != null) {
				System.out.println("Enter students count::");
				count = Integer.parseInt(sc.nextLine());
			}
			//register JDBC driver
			//Class.forName("com.mysql.cj.jdbc.driver");
			//establish the connection
			con = DriverManager.getConnection("jdbc:mysql:///prudvi", "root", "root");
			//create prepared statement obj having pre-compiled SQL query
			if(con != null) 
				ps = con.prepareStatement(STUDENT_INSERT_QUERY);
			//read input values from end user and set them to query params of pre-compiled query for multiple times
			if(ps != null && sc != null) {
				for(int i=1; i<=count;i++) {
					System.out.println("Enter "+i+" student details");
					System.out.println("enter student name:");
					String name = sc.nextLine();
					System.out.println("enter student address:");
					String addrs = sc.nextLine();
					System.out.println("enter student avg:");
					float avg = Float.parseFloat(sc.nextLine());
					//set each student details to pre-compiled sql query
					ps.setString(1, name);ps.setString(2, addrs);ps.setFloat(3, avg);
					//execute the pre-compiled sql query each time
					int result = ps.executeUpdate();
					//process the result
					if(result == 0)
						System.out.println("Student details are not inserted");
					else
						System.out.println("Student details are inserted");
				}//for
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
			try {
				if(ps != null)
					ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con != null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(sc != null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally

	}//main

}//class
