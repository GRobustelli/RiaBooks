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
public class Modificalibroadmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modificalibroadmin() {
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
		System.out.println("\n\nCi arrivo almeno?");
		String action = request.getParameter("action");
		UserBean  user = (UserBean)request.getSession().getAttribute("user");
	
		if (user != null) {
			if (user.isAdmin()) {
				if (action != null) {
				if (action.equals("invio")) {
					
					String id = request.getParameter("libro_id");
					try {
						LibroBean modlib = (LibroBean)libro.doRetrieveByKey(id);
						if (modlib.getId() != null) {
							
							System.out.println("Stiamo dentro a modlib");
						
							request.setAttribute("modlib", modlib);
							RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/modificalibro.jsp");
							dispatcher.forward(request, response);
						}
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
				}
				if (action.equals("do_mod")) {
					
					String titolo = request.getParameter("titolo");
					String autore = request.getParameter("autore");
					String categoria = request.getParameter("categoria");
					String descrizione = request.getParameter("descrizione");
					String libro_id = request.getParameter("libro_id");
					float prezzo = -1;
					
					if (!request.getParameter("prezzo").trim().isEmpty()) {
						prezzo = Float.parseFloat(request.getParameter("prezzo"));
					}
					try {
					if (!titolo.isBlank()) { libro.doUpdate(libro_id, "titolo", titolo);}
					
					if (!autore.isBlank()) {libro.doUpdate(libro_id, "autore", autore);}
					
					if (!categoria.isBlank()) {libro.doUpdate(libro_id, "categoria",categoria);}
					
					if (!descrizione.isBlank()) {libro.doUpdate(libro_id, "descrizione", descrizione);}
					
					if (prezzo >0 ) {libro.doUpdate(libro_id, prezzo);}
					
					} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					response.sendRedirect(request.getContextPath() + "/Admin/AdminHome.jsp");
					
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
