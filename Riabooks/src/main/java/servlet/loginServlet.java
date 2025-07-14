package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.UserBean;
import unisa.db.DriverManagerConnectionPool;
import unisa.db.ILibroDAO;
import unisa.db.IUserDAO;
import unisa.db.LibroDaoDriverMan;
import unisa.db.UserDaoDriverMan;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		List<String> errors = new ArrayList<>();
    	RequestDispatcher dispatcherToLoginPage = request.getRequestDispatcher("Login.jsp");
    	if(email == null || email.trim().isEmpty()) {
			errors.add("Il campo username non può essere vuoto!");

		}
        if(password == null || password.trim().isEmpty()) {
        	errors.add("Il campo password non può essere vuoto!");
		}
        if (!errors.isEmpty()) {
        	request.setAttribute("errors", errors);
        	dispatcherToLoginPage.forward(request, response);
        	return; 
        }
        
        email = email.trim();
        password = hashfunc.hashfunction(password);
        
        DriverManagerConnectionPool dm = (DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager");
		IUserDAO user = new UserDaoDriverMan(dm);
		
		try {
			UserBean bean = user.doRetrieveByKey(email);
			
			
			System.out.println(password + " \n\n" + bean.getPass() + "\n");
			
			
			
			if (!password.equals(bean.getPass() ))
			{
				
				System.out.println("\n\npassword sbagliata");
				errors.add("Username o password non corretti");
				request.setAttribute("errors", errors);
				dispatcherToLoginPage.forward(request, response);
				
				return;
			}
			else {
				request.getSession().setAttribute("isAdmin", bean.isAdmin());
				request.getSession().setAttribute("user", bean);
				if (bean.isAdmin()) {
					response.sendRedirect(""); //AGGIUNGERE JSP ADMIN
				}
				else
				{
					response.sendRedirect(request.getContextPath() + "/home.jsp");
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
