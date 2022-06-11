package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.DoctorDAO;
import com.javaBeans.HomeData;

/**
 * Servlet implementation class S_Home
 */
@WebServlet("/S_Home")
public class S_Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S_Home() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// call Dao to bring data from database
		DoctorDAO doctorDAO = new DoctorDAO();
		HomeData homeData;
		homeData = doctorDAO.getData();
		request.setAttribute("homeData",homeData);
		this.getServletContext().getRequestDispatcher("/clinic/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
