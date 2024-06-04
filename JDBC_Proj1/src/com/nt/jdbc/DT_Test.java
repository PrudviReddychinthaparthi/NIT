package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/*JDBC App that drops table as per the query
version:: 1.0
author:: prudvi
*/
public class DT_Test {

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
			String query = "DROP TABLE TEMP_STUDENT";
			System.out.println(query);
			
			//send and execute the SQL query in DB  s/w
			int count = 0;
			if(st != null) 
				count = st.executeUpdate(query);
				System.out.println("count :: "+count);
				//process the result
				if(count == 0)
					System.out.println("DB table is dropped");
				else
					System.out.println("DB table is not dropped");
			}//try
			catch(SQLException se) {
				se.printStackTrace();
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
