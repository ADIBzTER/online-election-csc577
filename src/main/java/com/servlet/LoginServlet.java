package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.*;
import com.dao.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userType = request.getParameter("userType");
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");

		// Voter or Candidate
		if (userType.equals("voter") || userType.equals("candidate")) {

			StudentBean student = new StudentBean();
			student.setUserId(userId);
			student.setPassword(password);

			// Log in & get student's data
			student = StudentDAO.login(student);

			try {
				if (student.isValid()) {
					// Logged-in page
					request.getSession(true).invalidate();
					HttpSession session = request.getSession(true);

					session.setAttribute("loggedIn", true);
					session.setAttribute("userId", student.getUserId());
					session.setAttribute("name", student.getName());
					session.setAttribute("faculty", student.getFaculty());

					if (userType.equals("voter")) {
						if (VoterDAO.checkIsVoted(student.getUserId())) {
							request.getSession(true).invalidate();

							RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
							request.setAttribute("errorMessage", "You already voted");
							rd.forward(request, response);
							return;
						}

						System.out.println(student.getUserId() + " logged in as Voter.");
						response.sendRedirect("vote");
					} else if (userType.equals("candidate")) {
						if (CandidateDAO.checkIsRegistered(student.getUserId())) {
							request.getSession(true).invalidate();

							RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
							request.setAttribute("errorMessage", "You already registered as candidate");
							rd.forward(request, response);
							return;
						}

						System.out.println(student.getUserId() + " logged in as Voter.");
						response.sendRedirect("candidate");
					}

				} else {
					// Error page
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					request.setAttribute("errorMessage", "Invalid Credentials");
					rd.forward(request, response);
				}
			} catch (Throwable e) {
				System.out.println(e);
			}

			// Admin
		} else if (userType.equals("admin")) {

			AdminBean admin = new AdminBean();
			admin.setUserId(userId);
			admin.setPassword(password);

			// Log in & get student's data
			admin = AdminDAO.login(admin);

			try {
				if (admin.isValid()) {
					// Logged-in page
					request.getSession(true).invalidate();
					HttpSession session = request.getSession(true);

					session.setAttribute("loggedIn", true);
					session.setAttribute("userId", admin.getUserId());
					session.setAttribute("name", admin.getName());

					System.out.println(admin.getUserId() + " logged in as Admin.");
					response.sendRedirect("admin");

				} else {
					// Error page
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					request.setAttribute("errorMessage", "Invalid Credentials");
					rd.forward(request, response);
				}
			} catch (Throwable e) {
				System.out.println(e);
			}

		}

	}
}
