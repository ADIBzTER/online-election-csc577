package com.dao;

import java.sql.*;

import com.config.*;
import com.bean.*;

public class VoterDAO {
	static Connection connection = null;
	static ResultSet resultSet = null;

	// Login voter
	public static VoterBean login(VoterBean voter) {

		// Preparing some objects for connection
		PreparedStatement statement = null;
		String userId = voter.getUserId();
		String password = voter.getPassword();

		// Prepared statement
		String sql = "SELECT * FROM voters WHERE v_userid=? AND v_password=?;";

		// Used to trace the process
		System.out.println("in VoterLogin.login");
		System.out.println("Your userId is " + userId);
		System.out.println("Your password is " + password);
		System.out.println("Query: " + sql);

		try {
			// Connect to lipan_db
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(sql);

			statement.setString(1, userId);
			statement.setString(2, password);

			resultSet = statement.executeQuery();
			boolean more = resultSet.next();

			// If voter does not exist
			if (!more) {
				System.out.println("Sorry, you are not a registered user! Please sign up first");
				voter.setValid(false);
			}
			// If user exists
			else if (more) {
				String name = resultSet.getString("v_name");
				String faculty = resultSet.getString("v_faculty");

				System.out.println("Welcome " + name);
				voter.setUserId(userId);
				voter.setName(name);
				voter.setFaculty(faculty);
				voter.setValid(true);
			}
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
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
		return voter;
	}
}
