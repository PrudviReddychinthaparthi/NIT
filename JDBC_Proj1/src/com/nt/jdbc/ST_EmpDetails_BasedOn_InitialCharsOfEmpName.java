package com.nt.jdbc;
/*JDBC App that gets the employee details from EMP DB table based on the given initial characters of Emp name
version:: 1.0
author:: prudvi
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*Java App to get the employee details based on the initial characters of Employee name*/

import java.util.Scanner;

public class ST_EmpDetails_BasedOn_InitialCharsOfEmpName {

	public static void main(String[] args) {
		System.out.println("SelectTest main()");
		//read inputs from end user
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		
		try {
			sc = new Scanner(System.in);
			String initChars = null;
			if(sc != null) {
				System.out.println("Enter the initial characters of employee name:");
				initChars = sc.next().toUpperCase(); 
			}
			//convert inputs as required for the SQL query
			initChars = "'"+initChars+"%'";
			
			//register the JDBC driver by loading JDBC driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "prudvi", "prudvi");
			
			//Create statement object
			if(con != null)
				st = con.createStatement();
			
			//prepare SQL query
			String query = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE "+initChars;
			
			//send and execute SQL Query
			if(st != null)
				rs = st.executeQuery(query);
			
			//process the ResultSet object (0 or more records found
			if(rs != null) {
				boolean isRecordFound = false;
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
					isRecordFound = true;
				}//while
				if(isRecordFound == false)
					System.out.println("No Records Found");
				
			}//if
			
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("Invalid col names or table names of SQL keywords");
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objects
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
