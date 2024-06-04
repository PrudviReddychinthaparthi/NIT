package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*JDBC App that modifies records of student DB table (increase avg by given percentage) based on given city names
version:: 1.0
author:: prudvi
*/
public class UT_IncreaseAvg_BasedOnGivenCities {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		
		try {
			//read inputs from enduser
			sc = new Scanner(System.in);
			float incrementInAvg = 0.0f;
			String city1 = null , city2 = null, city3 = null;
			if(sc != null) {
				System.out.println("Enter the increase in avg::");
				incrementInAvg = Float.parseFloat(sc.nextLine());
				System.out.println("Enter city1::");
				city1 = sc.nextLine();
				System.out.println("Enter city2::");
				city2 = sc.nextLine();
				System.out.println("Enter city3::");
				city3 = sc.nextLine();
			}
			//convert input values as required by SQL query
			city1 = "'"+city1+"'";
			city2 = "'"+city2+"'";
			city3 = "'"+city3+"'";
			
			//load the JDBC driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "prudvi", "prudvi");
			
			//Create statement object
			if(con != null)
				st = con.createStatement();
			
			//prepare SQL query
			//update student set avg=avg*(1+10/100) where sadd in ('hyd,'vizag','mumbai')
			String query = "UPDATE STUDENT SET AVG=AVG*(1+"+incrementInAvg+"/100) WHERE SADD IN ("+city1+","+city2+","+city3+")";
			System.out.println("SQL query:: "+query);
			int count = 0;
			//send and execute the query
			if(st != null)
			count = st.executeUpdate(query);
			//process the result
			if(count == 0)
				System.out.println("No Records found to update");
			else
				System.out.println("No.of records affected:: "+count);
			
		}catch(SQLException se) {
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
