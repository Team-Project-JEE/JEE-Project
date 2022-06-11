package com.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.MedicalFileDAO;
import com.DAO.PatientDAO;
import com.javaBeans.MedicalFile;
import com.javaBeans.User;

/**
 * Servlet implementation class Info_patients
 */
@WebServlet("/Info_patients")
public class Info_patients extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Info_patients() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
		int id_patient = user.getId_user();
		MedicalFileDAO medicalFileDao = new MedicalFileDAO();
		
		try {
			MedicalFile medicalFile = medicalFileDao.getMedicalFileById(id_patient);
			
			request.setAttribute("medicalFile",medicalFile);
			this.getServletContext().getRequestDispatcher("/clinic/Info_patient.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String date_naiss = request.getParameter("date_naiss");
		String Sex = request.getParameter("Sex");
		
		PatientDAO patientDAO = new PatientDAO();
		try {
			patientDAO.ModifierPation(id, prenom, nom, tel, email, date_naiss, Sex);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("action", "edit");
		doGet(request, response);
	}

}
