package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/*JDBC App that allows to check the login validation based on the data(username and password available in DB table
version:: 1.0
author:: prudvi
*/
public class LoginApp {

	public static void main(String[] args) {
		
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			//read inputs
			sc = new Scanner(System.in);
			String user = null, pass = null;
			if(sc != null) {
				System.out.println("Enter username to login: ");
				user = sc.nextLine();
				System.out.println("Enter password to login");
				pass = sc.nextLine();
			}//if
			//convert input values as required for the SQL query
			user = "'"+user+"'";
			pass = "'"+pass+"'";
			
			//Load JDBC driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "prudvi", "prudvi");
			
			//create JDBC statement object
			if(con != null)
				st = con.createStatement();
			//prepare SQL query
			String query = "SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME="+user+" AND PWD="+pass;
			System.out.println(query);
			//send and execute the SQL query in DB s/w
			if(st != null)
				rs= st.executeQuery(query);
			
			//process the resultset obj
			if(rs != null) {
				rs.next();//moves the cursor to first record from BFR
				int count = rs.getInt(1);//get first column value of first record
				
				//process the result
				if(count == 0)
					System.out.println("Invalid credentials");
				else
					System.out.println("Valid credentials");
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
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st != null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(con != null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
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
