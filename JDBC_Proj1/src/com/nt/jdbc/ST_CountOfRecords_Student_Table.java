package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*JDBC App that gets the count of records from STUDENT DB table
version:: 1.0
author:: prudvi
*/
public class ST_CountOfRecords_Student_Table {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			//Load the JDBC driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","prudvi","prudvi");
			
			//create JDBC statement obj
			if(con != null) {
				st = con.createStatement();
			}
			//prepare SQL query
			String query = "SELECT COUNT(*) FROM STUDENT";
			
			//send and execute SQL query
			if(st != null) {
				rs = st.executeQuery(query);
			}
			//process the Result set (0 or 1 record)
			if(rs != null) {
				rs.next();
				//int count = rs.getInt(1);
				int count = rs.getInt("COUNT(*)");
				System.out.println("Records count in Student DB table :: "+count);
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
			
		}//finally

	}//main

}//class
