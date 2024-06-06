package com.nt.jdbc.ps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PSLoginApp {
	private static final String LOGIN_QUERY = "SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME=? AND PWD=?";

	public static void main(String[] args) {

		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// read inputs
			sc = new Scanner(System.in);
			String user = null, pass = null;
			if (sc != null) {
				System.out.println("Enter username to login: ");
				user = sc.nextLine();
				System.out.println("Enter password to login");
				pass = sc.nextLine();
			} // if
				
			// Load JDBC driver class
			// Class.forName("oracle.jdbc.driver.OracleDriver");

			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "prudvi", "prudvi");

			// create JDBC statement object
			if (con != null)
				ps = con.prepareStatement(LOGIN_QUERY);
			//set values to the params of pre-compiled query
			if(ps != null) {
				ps.setString(1, user);
				ps.setString(2, pass);
			}
			
			// send and execute the SQL query in DB s/w
			if (ps != null)
				rs = ps.executeQuery();

			// process the resultset obj
			if (rs != null) {
				rs.next();// moves the cursor to first record from BFR
				int count = rs.getInt(1);// get first column value of first record

				// process the result
				if (count == 0)
					System.out.println("Invalid credentials");
				else
					System.out.println("Valid credentials");
			} // if
		} // try
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (ps != null)
					ps.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (sc != null)
					sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // finally

	}// main

}
