package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*JDBC App that gets the department details from DEPT DB tables based on the given dept no
version:: 1.0
author:: prudvi
*/



public class ST_DeptDetails_Of_Given_DeptNo {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			//read inputs from end user
			sc = new Scanner(System.in);
			int dno = 0;
			if(sc != null) {
				System.out.println("Enter department number::");
				dno = sc.nextInt();
				}
			 //Load JDBC driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "prudvi", "prudvi");
			
			//create JDBC statement object
			if(con != null)
				st = con.createStatement();
			
			//Prepare SQL query
			String query = "SELECT DEPTNO,DNAME,LOC FROM DEPT WHERE DEPTNO="+dno;
			System.out.println(query);
			
			//send and execute SQL query
			if(st != null) {
				rs = st.executeQuery(query);
			}
			//process the result set obj (0 or 1 record)
			if(rs != null) {
				if(rs.next())
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
				else
					System.out.println("No Records Found");
			}//if
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null)
					rs.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(st != null)
					st.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con != null)
					con.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(sc != null)
					sc.close();
			}//try
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}//finally

	}//main

}//class
