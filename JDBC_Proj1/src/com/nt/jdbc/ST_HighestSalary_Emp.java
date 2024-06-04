package com.nt.jdbc;
/*JDBC App that gives emp details who has the highest salary
version:: 1.0
author:: prudvi
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ST_HighestSalary_Emp {
	
	public static void main(String[] args) {
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			//Load the driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "prudvi", "prudvi");
			
			//create statement obj
			if(con != null) {
				st = con.createStatement();
			}
			
			//prepare SQL query
			String query = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP)";
			
			//send and execute the SQL query
			if(st != null) {
				rs = st.executeQuery(query);
			}
			
			//process the result set obj (0 or more records)
			if(rs != null) {
				boolean isRecordFound = false;
				while(rs.next()) {
					isRecordFound = true;
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				}
				if(isRecordFound == false)
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
