package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.*;
import com.dao.*;

@WebServlet("/admin")

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Admin not logged in
		Object loggedIn = request.getSession(true).getAttribute("admin");
		if (loggedIn == null) {
			response.sendRedirect("login");
			return;
		}

		// End Election
		if (request.getParameter("endElection") != null) {
			try {
				AdminDAO.endElection();
				response.sendRedirect("admin");
			} catch (Throwable e) {
				System.out.println(e);
			}
		}
		// Delete candidate
		else if (request.getParameter("decline") != null) {
			try {
				CandidateDAO.deleteOne(request.getParameter("userId"));
				response.sendRedirect("admin");
			} catch (Throwable e) {
				System.out.println(e);
			}
		}
		// Display candidates
		else if (request.getParameter("candidateId") == null) {
			try {
				List<CandidateBean> candidateList = CandidateDAO.getAll();
				request.setAttribute("candidateList", candidateList);

				RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
				rd.forward(request, response);

			} catch (Throwable e) {
				System.out.println(e);
			}
		}
		// Show candidate details (Approved)
		else if (request.getParameter("approved") != null) {
			String candidateId = request.getParameter("candidateId");

			CandidateBean candidate = CandidateDAO.getOne(candidateId);
			request.setAttribute("candidate", candidate);

			RequestDispatcher rd = request.getRequestDispatcher("candidateDetails.jsp");
			rd.forward(request, response);
		}
		// Show candidate details (Pending)
		else if (request.getParameter("approved") == null) {
			String candidateId = request.getParameter("candidateId");

			CandidateBean candidate = CandidateDAO.getOne(candidateId);
			request.setAttribute("candidate", candidate);

			RequestDispatcher rd = request.getRequestDispatcher("candidateDetails.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CandidateDAO.approve(request.getParameter("userId"));
		response.sendRedirect("admin");

//		String userRegistered = registerDAO.registerUser(candidate);

//		if (userRegistered.equals("SUCCESS")) {
//			request.getRequestDispatcher("");
//		} else {
//			request.setAttribute("errMessage", userRegistered);
//			request.getRequestDispatcher("");
//		}
//		RequestDispatcher rd = request.getRequestDispatcher("login");
//		rd.forward(request, response);
	}
}
