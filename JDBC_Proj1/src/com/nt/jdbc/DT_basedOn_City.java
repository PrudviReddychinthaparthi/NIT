package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/*JDBC App that deletes records from STUDENT DB table based on given city of student
version:: 1.0
author:: prudvi
*/
public class DT_basedOn_City {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		
		try {
			//read inputs from end user
			sc = new Scanner(System.in);
			String city = null;
			if(sc != null) {
				System.out.println("Enter student address (city name) ::");
				city = sc.next();
			}
			//convert inputs as required for the SQL query
			city = "'"+city+"'";
			
			 //Load JDBC driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "prudvi", "prudvi");
			
			//create JDBC statement object
			if(con != null)
				st = con.createStatement();
			
			//prepare SQL query
			//delete from student where sadd='hyd'
			String query = "DELETE FROM STUDENT WHERE SADD="+city;
			System.out.println("SQL Query:: "+query);
			
			//Send and execute SQL query
			int count = 0;
			if(st != null)
				count = st.executeUpdate(query);
			
			//process the result
			if(count == 0)
				System.out.println("No Records found to delete");
			else
				System.out.println("No.of records affected: "+count);
		}//try
		catch(SQLException se) {
			if(se.getErrorCode() >=900 && se.getErrorCode() <= 999)
				System.out.println("Invalid column names or tables names or SQL keywords");
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
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
