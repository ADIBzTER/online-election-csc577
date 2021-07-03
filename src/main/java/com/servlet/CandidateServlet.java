package com.servlet;

import java.io.IOException;
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

@WebServlet("/candidate")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)

public class CandidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("candidateRegister.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Copying all the input parameters in to local variables
		String userId = request.getParameter("userId");
		String achievement = request.getParameter("achievement");
		String manifesto = request.getParameter("manifesto");

		try {
			String uploadPath = "C:\\Users\\Adib Zaini\\Desktop\\CS230 PART 4\\CSC577 - Software Engineering Theories and Principles\\Project\\OnlineElection\\src\\main\\webapp\\images\\";

			Part part = request.getPart("photo");

			// Upload image to server
			String fileName = ImageUploader.upload(uploadPath, part);

			System.out.println(fileName + " uploaded");

			// Using Java Beans - An easiest way to play with group of related data
			CandidateBean candidate = new CandidateBean();
			candidate.setUserId(userId);
			candidate.setImageLocation("images/" + fileName);
			candidate.setAchievement(achievement);
			candidate.setManifesto(manifesto);

			CandidateDAO.addOne(candidate);

			response.sendRedirect("login");

		} catch (Throwable e) {
			System.out.println(e);
		}

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
