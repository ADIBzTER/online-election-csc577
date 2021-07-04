package com.dao;

import java.sql.*;
import java.util.*;

import com.config.*;
import com.bean.*;

public class CandidateDAO {
	static Connection connection = null;
	static ResultSet resultSet = null;

	public static void addOne(CandidateBean candidate) {
		String userId = candidate.getUserId();
		String imageLocation = candidate.getImageLocation();
		String achievement = candidate.getAchievement();
		String manifesto = candidate.getManifesto();

		PreparedStatement preparedStatement = null;

		// Trace process
		System.out.println("In CandidateDAO.addOne");
		try {

			connection = ConnectionManager.getConnection();

			String sql = "INSERT INTO candidates(c_userid, c_image_location, c_achievement, c_manifesto) VALUES (?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, imageLocation);
			preparedStatement.setString(3, achievement);
			preparedStatement.setString(4, manifesto);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<CandidateBean> getAll() {
		// Preparing some objects/variable
		List<CandidateBean> candidateList = new LinkedList<>();
		String sql = "SELECT * FROM candidates c JOIN students s ON c.c_userid=s.s_userid;";

		Statement statement = null;

		// Trace process
		System.out.println("in CandidateDAO.getAll");

		try {
			// Connect to DB
			connection = ConnectionManager.getConnection();

			statement = connection.createStatement();

			resultSet = statement.executeQuery(sql);

			// Iterate over the ResultSet, add row into object and object into list
			while (resultSet.next()) {

				CandidateBean candidate = new CandidateBean();
				candidate.setUserId(resultSet.getString("s_userid"));
				candidate.setName(resultSet.getString("s_name"));
				candidate.setFaculty(resultSet.getString("s_faculty"));
				candidate.setImageLocation(resultSet.getString("c_image_location"));
				candidate.setAchievement(resultSet.getString("c_achievement"));
				candidate.setManifesto(resultSet.getString("c_manifesto"));
				candidate.setApproved(resultSet.getBoolean("c_approved"));
				candidate.setVotes(resultSet.getInt("c_votes"));

				candidateList.add(candidate);
			}
		} catch (Exception e) {
			System.out.println("Error in CandidateDAO.getAll " + e);
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
		return candidateList;
	}

	public static List<CandidateBean> getAllApproved() {
		// Preparing some objects/variable
		List<CandidateBean> candidateList = new LinkedList<>();
		String sql = "SELECT * FROM candidates c JOIN students s ON c.c_userid=s.s_userid WHERE c_approved=1;";

		Statement statement = null;

		// Trace process
		System.out.println("in CandidateDAO.getAll");

		try {
			// Connect to DB
			connection = ConnectionManager.getConnection();

			statement = connection.createStatement();

			resultSet = statement.executeQuery(sql);

			// Iterate over the ResultSet, add row into object and object into list
			while (resultSet.next()) {

				CandidateBean candidate = new CandidateBean();
				candidate.setUserId(resultSet.getString("s_userid"));
				candidate.setName(resultSet.getString("s_name"));
				candidate.setFaculty(resultSet.getString("s_faculty"));
				candidate.setImageLocation(resultSet.getString("c_image_location"));
				candidate.setAchievement(resultSet.getString("c_achievement"));
				candidate.setManifesto(resultSet.getString("c_manifesto"));
				candidate.setVotes(resultSet.getInt("c_votes"));

				candidateList.add(candidate);
			}
		} catch (Exception e) {
			System.out.println("Error in CandidateDAO.getAll " + e);
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
		return candidateList;
	}

	// Get only one candidate
	public static CandidateBean getOne(String userId) {

		// Preparing some objects/variable
		CandidateBean candidate = new CandidateBean();
		String sql = "SELECT * FROM candidates c JOIN students s ON c.c_userid=s.s_userid WHERE c_userid=?;";

		PreparedStatement statement = null;

		// Trace process
		System.out.println("in CandidateDAO.getOne");

		try {
			// Connect to DB
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(sql);

			statement.setString(1, userId);

			resultSet = statement.executeQuery();

			// Iterate over the ResultSet, add row into object and object into list
			while (resultSet.next()) {
				candidate.setUserId(resultSet.getString("s_userid"));
				candidate.setName(resultSet.getString("s_name"));
				candidate.setFaculty(resultSet.getString("s_faculty"));
				candidate.setImageLocation(resultSet.getString("c_image_location"));
				candidate.setAchievement(resultSet.getString("c_achievement"));
				candidate.setManifesto(resultSet.getString("c_manifesto"));
				candidate.setVotes(resultSet.getInt("c_votes"));
			}
		} catch (Exception e) {
			System.out.println("Error in CandidateDAO.getOne " + e);
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
		return candidate;
	}

	public static int getVotes(String userId) {

		// Preparing some objects for connection
		PreparedStatement statement = null;
		int votes = 0;

		// Prepared statement
		String sql = "SELECT c_votes FROM candidates WHERE c_userid = ?";

		// Used to trace the process
		System.out.println("in CandidateDAO.getVote");

		try {
			// Connect to lipan_db
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(sql);

			statement.setString(1, userId);

			resultSet = statement.executeQuery();

			// Iterate over the ResultSet, add row into object and object into list
			if (resultSet.next()) {
				votes = resultSet.getInt("c_votes");
			}

			System.out.println("Get vote");

		} catch (Exception ex) {
			System.out.println("Error in ProductDAO.updateOne" + ex);
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
		return votes;
	}

	public static void addVote(String userId) {

		// Preparing some objects for connection
		PreparedStatement statement = null;

		// Prepared statement
		String sql = "UPDATE candidates SET c_votes = ? WHERE c_userid = ?;";

		// Used to trace the process
		System.out.println("in CandidateDAO.addVote");

		try {
			// Connect to lipan_db
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(sql);

			statement.setInt(1, CandidateDAO.getVotes(userId) + 1);
			statement.setString(2, userId);

			statement.executeUpdate();
			System.out.println("Vote Added");

		} catch (Exception e) {
			System.out.println("Error in CandidateDAO.addVote" + e);
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
}