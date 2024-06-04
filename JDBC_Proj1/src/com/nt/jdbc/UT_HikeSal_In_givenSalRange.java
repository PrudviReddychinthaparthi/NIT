package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/*JDBC App that modifies records of emp DB table (hike emp salary by given percentage) based on given range of salary
version:: 1.0
author:: prudvi
*/
public class UT_HikeSal_In_givenSalRange {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		
		try {
			//read inputs from end user
			sc = new Scanner(System.in);
			Float startRangeofSalary = 0.0f, endRangeOfSalary = 0.0f, hikePercent = 0.0f;
			if(sc != null) {
				System.out.println("Enter start range of salary ::");
				startRangeofSalary = sc.nextFloat();
				System.out.println("Enter end range of salary ::");
				endRangeOfSalary = sc.nextFloat();
				System.out.println("Enter hike percentage ::");
				hikePercent = sc.nextFloat();
				
			}
			
			
			 //Load JDBC driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "prudvi", "prudvi");
			
			//create JDBC statement object
			if(con != null)
				st = con.createStatement();
			
			//prepare SQL query
			//update employee set sal=sal*(1+10/100) where sal between 500 and 2500
			String query = "UPDATE EMP SET SAL=SAL*(1+"+hikePercent+"/100) WHERE SAL BETWEEN "+startRangeofSalary+" AND "+endRangeOfSalary;
			System.out.println("SQL Query:: "+query);
			
			//Send and execute SQL query
			int count = 0;
			if(st != null)
				count = st.executeUpdate(query);
			
			//process the result
			if(count == 0)
				System.out.println("No Records found to update");
			else
				System.out.println("No.of records affected: "+count);
		}//try
		catch(SQLException se) {
			if(se.getErrorCode() >=900 && se.getErrorCode() <= 999)
				System.out.println("Invalid column names or tables names or SQL keywords");
			else if(se.getErrorCode() == 12899)
				System.out.println("Do not insert more than col size data to sname, sadd columns");
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objects
			try {
				if(st != null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con != null) {
					con.close();
				}
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
