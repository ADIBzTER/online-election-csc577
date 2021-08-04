package com.dao;

import java.sql.*;

import com.config.*;
import com.bean.*;

public class VoterDAO {
	static Connection connection = null;
	static ResultSet resultSet = null;

	public static void voted(VoterBean voter) {

		// Preparing some objects for connection
		PreparedStatement statement = null;

		// Prepared statement
		String sql = "INSERT INTO voters (v_userid, v_voted) VALUES(?, ?)";

		// Used to trace the process
		System.out.println("in VoterDAO.voted");

		try {
			// Connect to lipan_db
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(sql);

			statement.setString(1, voter.getUserId());
			statement.setBoolean(2, true);

			statement.executeUpdate();
			System.out.println("Voter voted");

		} catch (Exception e) {
			System.out.println("Error in VoterDAO.voted" + e);
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
	}

	public static boolean checkIsVoted(String userId) {
		boolean voted = false;

		// Preparing some objects for connection
		PreparedStatement statement = null;

		// Prepared statement
		String sql = "SELECT v_voted FROM voters WHERE v_userid=?";

		// Used to trace the process
		System.out.println("in VoterDAO.checkIsVoted");

		try {
			// Connect to lipan_db
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(sql);

			statement.setString(1, userId);

			resultSet = statement.executeQuery();

			// Voter exists
			if (resultSet.next()) {
				voted = true;
			}

		} catch (Exception e) {
			System.out.println("Error in VoterDAO.checkIsVoted" + e);
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
		return voted;
	}
}
