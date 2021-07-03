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
		String userId;
		String password;

		// Voter or Candidate
		if (userType.equals("voter") || userType.equals("candidate")) {
			userId = request.getParameter("userId");
			password = request.getParameter("password");

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
						System.out.println(student.getUserId() + " logged in as Voter.");
						response.sendRedirect("vote");
					} else if (userType.equals("candidate")) {
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

		}

	}
}
