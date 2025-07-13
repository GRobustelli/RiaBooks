package controllo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.Cart;
import unisa.ContieneBean;
import unisa.LibroBean;
import unisa.UserBean;
import unisa.db.ContieneDaoDriverMan;
import unisa.db.DriverManagerConnectionPool;
import unisa.db.IContieneDAO;
import unisa.db.ILibroDAO;
import unisa.db.LibroDaoDriverMan;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class CartControl
 */
@WebServlet("/CartControl")
public class CartControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("\n\nSto nella servlet CartControl");
		
		DriverManagerConnectionPool dm = (DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager");
		IContieneDAO cont = new ContieneDaoDriverMan(dm);
		ILibroDAO libro = new LibroDaoDriverMan(dm);
		
		UserBean utente = (UserBean) request.getSession().getAttribute("user");

		String action = request.getParameter("action");
		
		if (action != null)
		{
			try {
				if (action.equals("insert")) {
					
					Cart carrello = (Cart) request.getSession().getAttribute("cart");
					String libro_id = request.getParameter("libro_id");
					LibroBean nuovo = (LibroBean) libro.doRetrieveByKey(libro_id);
					carrello.addLibro(nuovo);
					
					System.out.println("Sono dopo l'inserimento");
					
					if (utente != null) {
						
						ContieneBean persistenza = new ContieneBean(utente.getEmail(),libro_id);
						cont.doSave(persistenza);
					}
					
					
				}
			}catch(SQLException e) {
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
