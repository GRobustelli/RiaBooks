package controllo;

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
 * Servlet implementation class UserControl
 */
@WebServlet("/UserControl")
public class UserControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	System.out.println("\n\nSto nella servlet UserControl");
		
		DriverManagerConnectionPool dm = (DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager");
		IUserDAO utente = new UserDaoDriverMan(dm);
		UserBean bean = new UserBean();
		UserBean new_utente = (UserBean) request.getAttribute("new_user");
		String action = request.getParameter("action");
		
		
		
		if (action != null) {
			try {
				
			
			if (action.equals("do_save")) {
				bean = utente.doRetrieveByKey(new_utente.getEmail());
				if (bean.getEmail() == null) {
					utente.doSave(new_utente);
					response.sendRedirect(request.getContextPath() + "/Login.jsp");
				}
				else {
					List<String> errors = new ArrayList<>();
					errors.add("Email già presente nel database");
					RequestDispatcher dispatcherToRegisterPage = request.getRequestDispatcher("Register.jsp");
					request.setAttribute("errors", errors);
					dispatcherToRegisterPage.forward(request,response);
					return;
					
				}
				
			}
		}catch(SQLException e)
			{
			
			e.printStackTrace();
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
