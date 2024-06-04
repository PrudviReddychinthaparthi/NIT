package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*JDBC App that executes the query given by end user
version:: 1.0
author:: prudvi
*/
public class SelectNonSelectTest{

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			//read the desired nth highest salary required by enduser
			sc = new Scanner(System.in);
			String query = null;
			if(sc != null) {
				System.out.println("Enter SQL query::");
				query = sc.nextLine();
			}
			
			//Load the jdbc driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "prudvi", "prudvi");
			
			//create jdbc statement obj
			if(con != null)
				st = con.createStatement();
			
			//send and execute SQL query
			if(st != null) {
				boolean flag = st.execute(query);
				System.out.println(flag);
				if(flag) {
					System.out.println("Select SQL query executed");
					//gather and process ResultSet
					rs = st.getResultSet();
					//process ResultSet obj
					if(rs != null) {
						while(rs.next()) {
							System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
						}//while
					}//if
				}//if
				else {
					System.out.println("Non Select SQL query executed");
					int count = st.getUpdateCount();
					System.out.println("No.of records affected::"+count);
				}
			}//if
			
			
				
			
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally{
			try {
				if(rs != null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(st != null)
					st.close();
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
