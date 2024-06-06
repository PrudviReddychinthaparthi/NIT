package com.nt.jdbc.ps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PSDateITOracle {
	private static final String INSERT_DATE_QUERY="INSERT INTO PERSON_INFO_DATES VALUES(PID_SEQ.NEXTVAL,?,?,?,?)";

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			//read inputs
			sc = new Scanner(System.in);
			String name = null, sdob = null, sdoj = null, sdom = null;
			if(sc != null) {
				System.out.println("Enter Person name:");
				name = sc.nextLine();
				System.out.println("Enter DOB (dd-MM-yyyy):");
				sdob = sc.nextLine();
				System.out.println("Enter DOJ (yyyy-MM-dd)");
				sdoj = sc.nextLine();
				System.out.println("Enter DOM (MMM-dd-yy)");
				sdom = sc.nextLine();
			}//if
			//convert String date values into java.sql.Date class objs
			//for DOB(dd-MM-yyyy) 
			//convert String date value to java.util.Date class obj
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date udob = sdf1.parse(sdob);
			//converting java.util.Date class obj to java.sql.Date class obj
			long ms = udob.getTime();// this will get the diff b/w the given date and 1970 jan 1st midnight 00:00hrs
									 //(Epoach standard
			java.sql.Date sqdob = new java.sql.Date(ms);
			//for doj(yyyy-MM-dd direct conversion
			//converting String date class obj to java.sql.Date class obj
			java.sql.Date sqdoj = java.sql.Date.valueOf(sdoj);
			//for DOM(MMM-dd-yyyy) convert String date value to java.util.Date value
			SimpleDateFormat sdf2 = new SimpleDateFormat("MMM-dd-yyyy");
			java.util.Date udom = sdf2.parse(sdom);
			//converting java.util.Date value to java.sql.Date
			ms = udom.getTime();
			java.sql.Date sqdom = new java.sql.Date(ms);
			//Load jdbc driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "prudvi", "prudvi");
			//create PreparedStatement obj
			if(con != null)
				ps = con.prepareStatement(INSERT_DATE_QUERY);
			//set values to query params
			
			if(ps != null) {
				ps.setString(1, name);
				ps.setDate(2, sqdob);
				ps.setDate(3, sqdoj);
				ps.setDate(4, sqdom);
			}
			//execute query 
			int count = 0;
			if(ps != null)
				count = ps.executeUpdate();
			//process the results
			if(count == 0)
				System.out.println("Record not inserted");
			else
				System.out.println("Record inserted");
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(ps != null)
					ps.close();
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
