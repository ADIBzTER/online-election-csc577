package com.dao;

import java.sql.*;

import com.config.*;
import com.bean.*;

public class AdminDAO {
	static Connection connection = null;
	static ResultSet resultSet = null;

	// Login voter
	public static AdminBean login(AdminBean admin) {

		// Preparing some objects for connection
		PreparedStatement statement = null;
		String userId = admin.getUserId();
		String password = admin.getPassword();

		// Prepared statement
		String sql = "SELECT * FROM admins WHERE a_userid=? AND a_password=?;";

		// Used to trace the process
		System.out.println("in AdminDAO.login");
		System.out.println("Your userId is " + userId);
		System.out.println("Your password is " + password);
		System.out.println("Query: " + sql);

		try {
			// Connect to election_db
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(sql);

			statement.setString(1, userId);
			statement.setString(2, password);

			resultSet = statement.executeQuery();
			boolean more = resultSet.next();

			// If voter does not exist
			if (!more) {
				System.out.println("Admin not exists");
				admin.setValid(false);
			}
			// If user exists
			else if (more) {
				String name = resultSet.getString("a_name");

				System.out.println("Welcome " + name);
				admin.setUserId(userId);
				admin.setName(name);
				admin.setValid(true);
			}
		} catch (Exception e) {
			System.out.println("Log In failed: An Exception has occurred! " + e);
		}
		// Some exception handling
		finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception e) {
				}
				resultSet = null;
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception e) {
				}
				statement = null;
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
				}
				connection = null;
			}
		}
		return admin;
	}

//	// Get only one student
//	public static StudentBean getOne(String userId) {
//
//		// Preparing some objects/variable
//		StudentBean student = new StudentBean();
//		String sql = "SELECT * from students WHERE s_userid=?;";
//
//		PreparedStatement statement = null;
//
//		// Trace process
//		System.out.println("in StudentDAO.getOne");
//
//		try {
//			// Connect to DB
//			connection = ConnectionManager.getConnection();
//			statement = connection.prepareStatement(sql);
//
//			statement.setString(1, userId);
//
//			resultSet = statement.executeQuery();
//
//			// Iterate over the ResultSet, add row into object and object into list
//			while (resultSet.next()) {
//				student.setUserId(resultSet.getString("s_userid"));
//				student.setName(resultSet.getString("s_name"));
//				student.setFaculty(resultSet.getString("s_faculty"));
//			}
//		} catch (Exception e) {
//			System.out.println("Error in StudentdAO.getOne " + e);
//		}
//		// Some exception handling
//		finally {
//			if (resultSet != null) {
//				try {
//					resultSet.close();
//				} catch (Exception e) {
//				}
//				resultSet = null;
//			}
//			if (statement != null) {
//				try {
//					statement.close();
//				} catch (Exception e) {
//				}
//				statement = null;
//			}
//			if (connection != null) {
//				try {
//					connection.close();
//				} catch (Exception e) {
//				}
//				connection = null;
//			}
//		}
//		return student;
//	}
}
