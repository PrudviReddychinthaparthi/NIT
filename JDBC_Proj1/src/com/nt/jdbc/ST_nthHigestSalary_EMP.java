package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*JDBC App that gives emp details who is having nth highest salary
version:: 1.0
author:: prudvi
*/
public class ST_nthHigestSalary_EMP{

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			//read the desired nth highest salary required by enduser
			sc = new Scanner(System.in);
			int n = 0;
			if(sc != null) {
				System.out.println("Enter the n value to get nth  highest salary::");
				n = sc.nextInt();
			}
			
			//Load the jdbc driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "prudvi", "prudvi");
			
			//create jdbc statement obj
			if(con != null)
				st = con.createStatement();
			
			//prepare qsl query
			//select * from emp order by sal desc offset (n-1) rows fetch next 1 rows only;
			String query = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP ORDER BY SAL DESC OFFSET ("+n+"-1) ROWS FETCH NEXT 1 ROWS ONLY";
			//String query = "SELECT * FROM (SELECT EMPNO,ENAME,JOB,SAL, DENSE_RANK() OVER (ORDER BY SAL DESC) R FROM EMP) WHERE R="+n;
			System.out.println("SQL Query:: "+query);
			
			//send and execute SQL query
			if(st != null)
				rs = st.executeQuery(query);
			//process the result set (0 or 1 record)
			if(rs != null) {
				if(rs.next())
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				else
					System.out.println("No Record Found");
			}
			
			
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
