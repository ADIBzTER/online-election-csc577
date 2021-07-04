package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.config.*;
import com.bean.*;
import com.dao.*;

@WebServlet("/admin")

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("candidateId") == null) {
			try {
				List<CandidateBean> candidateList = CandidateDAO.getAll();
				request.setAttribute("candidateList", candidateList);

				RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
				rd.forward(request, response);

			} catch (Throwable e) {
				System.out.println(e);
			}
		} else {
			try {
				String candidateId = request.getParameter("candidateId");

				CandidateBean candidate = CandidateDAO.getOne(candidateId);
				request.setAttribute("candidate", candidate);

				RequestDispatcher rd = request.getRequestDispatcher("admin-verify.jsp");
				rd.forward(request, response);

			} catch (Throwable e) {
				System.out.println(e);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
