package controllo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.LibroBean;
import unisa.UserBean;
import unisa.db.DriverManagerConnectionPool;
import unisa.db.ILibroDAO;
import unisa.db.LibroDaoDriverMan;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class modificalibro
 */
@WebServlet("/modificalibro")
public class modificalibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modificalibro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n\nSto nella servlet modificalibro");
		
		DriverManagerConnectionPool dm = (DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager");
		ILibroDAO libro = new LibroDaoDriverMan(dm);
		
		String action = request.getParameter("action");
		UserBean  user = (UserBean)request.getSession().getAttribute("user");
		
		if (user != null) {
			if (user.isAdmin()) {
				if (action.equals("invio")) {
					
					String id = request.getParameter("libro_id");
					try {
						LibroBean modlib = (LibroBean)libro.doRetrieveByKey(id);
						if (modlib.getId() != null) {
							request.setAttribute("modlib", modlib);
							RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/modificalibro.jsp");
							dispatcher.forward(request, response);
						}
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
				}
				
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
