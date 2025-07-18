package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.UserBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		
		UserBean user = new UserBean();
		user.setEmail(request.getParameter("email").trim());
		user.setNome(request.getParameter("nome").trim());
		user.setCognome(request.getParameter("cognome").trim());
		user.setPass(hashfunc.hashfunction(request.getParameter("password")));
		user.setAdmin(false);
		List<String> errors = new ArrayList<>();
    	
		RequestDispatcher dispatcherToRegisterPage = request.getRequestDispatcher("Register.jsp");
    	RequestDispatcher dispatcherToUserControl = request.getRequestDispatcher("UserControl");
		if(user.getEmail() == null || user.getEmail().trim().isEmpty()) {
			errors.add("Il campo email non può essere vuoto!");
		}
        if(user.getNome() == null || user.getNome().trim().isEmpty()) {
        	errors.add("Il campo nome non può essere vuoto!");
		}
        if (user.getCognome()== null || user.getCognome().trim().isEmpty()) {
        	errors.add("Il campo cognome non può essere vuoto");
        }
        if (user.getPass() == null || user.getPass().trim().isEmpty())
        {
        	errors.add("Il campo password non può essere vuoto");
        }
        if (!errors.isEmpty()) {
        	request.setAttribute("errors", errors);
        	dispatcherToRegisterPage.forward(request, response);
        	return; 
        }
        
        request.setAttribute("new_user", user);
        
        dispatcherToUserControl.forward(request, response);
		
	}

}
