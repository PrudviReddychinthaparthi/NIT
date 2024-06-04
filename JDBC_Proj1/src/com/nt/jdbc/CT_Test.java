package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/*JDBC App that creates a table
version:: 1.0
author:: prudvi
*/
public class CT_Test {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		
		try {
			 //Load JDBC driver class
			//Class.forName("oracle.jdbc.OracleDriver");
			
			//Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "prudvi", "prudvi");
			
			//create JDBC statement object
			if(con != null)
				st = con.createStatement();
			//prepare SQL query
			String query = "CREATE TABLE TEMP_STUDENT(SNO NUMBER(5) PRIMARY KEY, SNAME VARCHAR(15))";
			System.out.println(query);
			
			//send and execute the SQL query in DB  s/w
			int count = 0;
			if(st != null) 
				count = st.executeUpdate(query);
				System.out.println("count :: "+count);
				//process the result
				if(count == 0)
					System.out.println("DB table is created");
				else
					System.out.println("DB table is not created");
			}//try
			catch(SQLException se) {
				se.printStackTrace();
				if(se.getErrorCode() == 955)
					System.out.println("DB table is already created");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				//close JDBC objs
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
					catch(SQLException e){
						e.printStackTrace();
					}
			}//finally

	}//main

}//class
