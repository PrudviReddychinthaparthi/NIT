package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*JDBC App that insert records into student DB table
version:: 1.0
author:: prudvi
*/
public class IT_EmployeeTable {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;

		try {
			// read inputs from end user
			sc = new Scanner(System.in);
			int empno = 0,deptno=0;
			Float sal = 0.0f;
			String ename = null, job = null;
			if (sc != null) {
				System.out.println("Enter employe no::");
				empno = Integer.parseInt(sc.nextLine());
				System.out.println("Enter name::");
				ename = sc.nextLine().toUpperCase();
				System.out.println("Enter job::");
				job = sc.nextLine().toUpperCase();
				System.out.println("Enter employee sal::");
				sal = Float.parseFloat(sc.nextLine());
				System.out.println("Enter employe dept no::");
				deptno = Integer.parseInt(sc.nextLine());

			}
			// convert input values as required by SQL query
			ename = "'" + ename + "'";
			job = "'" + job + "'";

			// Load JDBC driver class
			// Class.forName("oracle.jdbc.driver.OracleDriver");

			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "prudvi", "prudvi");

			// create JDBC statement object
			if (con != null)
				st = con.createStatement();

			// prepare SQL query
			// insert into emp (empno,ename,job,sal,deptno) values(7790,'RAJESH','CLERK',1500,10);
			String query = "INSERT INTO EMP (EMPNO,ENAME,JOB,SAL,DEPTNO) VALUES(" + empno + "," + ename+ ","+job+","+sal+","+deptno+")";
			System.out.println("SQL Query:: " + query);

			// Send and execute SQL query
			int count = 0;
			if (st != null)
				count = st.executeUpdate(query);

			// process the result
			if (count == 0)
				System.out.println("Records not inserted");
			else
				System.out.println("Records inserted ");
		} // try
		catch (SQLException se) {
			if (se.getErrorCode() >= 900 && se.getErrorCode() <= 999)
				System.out.println("Invalid column names or tables names or SQL keywords");
			else if (se.getErrorCode() == 12899)
				System.out.println("Do not insert more than col size data to sname, sadd columns");
			else if(se.getErrorCode() == 1400)
				System.out.println("Cannot insert null value to Primary key column");
			System.out.println("Problem in record insertion..");
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close jdbc objects
			try {
				if (st != null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (sc != null)
					sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // finally

	}// main

}// class
