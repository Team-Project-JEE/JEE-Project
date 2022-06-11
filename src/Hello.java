

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.DoctorDAO;
import com.DAO.UserDAO;
import com.javaBeans.HomeData;
import com.javaBeans.User;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/Hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hello() {
        super();
        // TODO Auto-generated constructor stub
    }

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	HttpSession session = request.getSession();
    	
    	if(session.getAttribute("user")!=null) {
    		User user = (User) session.getAttribute("user");
    		String  accountType = user.getAccountType() ;
            if(accountType.equals("doctor")) {
        		DoctorDAO doctorDAO = new DoctorDAO();
        		HomeData homeData;
        		homeData = doctorDAO.getData();
        		request.setAttribute("homeData",homeData);
            	this.getServletContext().getRequestDispatcher("/clinic/home.jsp").forward(request, response);
            }
            else if(accountType.equals("patient")) {
            	this.getServletContext().getRequestDispatcher("/clinic/home_patient.jsp").forward(request, response);
            }
    	}
    	
		this.getServletContext().getRequestDispatcher("/clinic/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserDAO userDAO = new UserDAO();
		User user = null ;
		
		try {
			user=userDAO.checkLogin(email, password);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		if(user != null) {
			HttpSession session = request.getSession();
            session.setAttribute("user", user);
            
            //V�rifiez si est un m�decin ou patient et dirige chacun vers sa espace
            String  accountType = user.getAccountType() ;
            if(accountType.equals("doctor")) {
        		DoctorDAO doctorDAO = new DoctorDAO();
        		HomeData homeData;
        		homeData = doctorDAO.getData();
        		request.setAttribute("homeData",homeData);
            	this.getServletContext().getRequestDispatcher("/clinic/home.jsp").forward(request, response);
            }
            else if(accountType.equals("patient")) {
            	this.getServletContext().getRequestDispatcher("/clinic/home_patient.jsp").forward(request, response);
            }
            				
		}
		else {
			String message = "Email et/ou Mot de passe incorrect(s)";
            request.setAttribute("message", message);
            doGet(request, response);                
		}
	}
}
