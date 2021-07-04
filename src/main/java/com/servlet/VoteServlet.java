package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.*;
import com.dao.*;

@WebServlet("/vote")
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Get html page
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<CandidateBean> candidateList = CandidateDAO.getAllApproved();

		request.setAttribute("candidateList", candidateList);

		RequestDispatcher rd = request.getRequestDispatcher("vote.jsp");
		rd.forward(request, response);
	}

	// Add vote to database
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("userId");

		CandidateDAO.addVote(userId);
		VoterDAO.voted(userId);

		response.sendRedirect("login");
	}

}
