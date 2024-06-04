package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/*JDBC App that modifies records of STUDENT DB table based on given student roll number
version:: 1.0
author:: prudvi
*/
public class UT_basedOn_Sno {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		
		try {
			//read inputs from end user
			sc = new Scanner(System.in);
			int sno = 0;
			String newName = null, newCity = null;
			Float newAvg = 0.0f;
			if(sc != null) {
				System.out.println("Enter student roll no ::");
				sno = Integer.parseInt(sc.nextLine());
				System.out.println("Enter student new name ::");
				newName = sc.nextLine();
				System.out.println("Enter student new city ::");
				newCity = sc.nextLine();
				System.out.println("Enter student new avg ::");
				newAvg = Float.parseFloat(sc.nextLine());
			}
			//convert input values as required for the SQL query
			newName = "'"+newName+"'";
			newCity = "'"+newCity+"'";
			
			 //Load JDBC driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "prudvi", "prudvi");
			
			//create JDBC statement object
			if(con != null)
				st = con.createStatement();
			
			//prepare SQL query
			//update student set sname='anil rao' , sadd='navi mumbai' , avg=91.35 where sno=105
			String query = "UPDATE STUDENT SET SNAME="+newName+" ,SADD="+newCity+" ,AVG="+newAvg+"WHERE SNO="+sno;
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
