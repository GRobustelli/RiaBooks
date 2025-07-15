package controllo;

import jakarta.servlet.RequestDispatcher;
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
import java.nio.file.FileSystemNotFoundException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
		String azione = (String) request.getAttribute("azione");
		Cart carrello = (Cart) request.getSession().getAttribute("cart");
		
		if (action != null)
		{
			try {
				if (action.equals("svuota")){
					carrello.deleteAll();
					if (utente != null) {
						cont.doDeleteAll(utente.getEmail());
						
					}
					response.setContentType("text/plain");
					response.getWriter().write("true");
					return;
					
				}
				
				if (action.equals("prezzi") && utente != null) {
					
					Collection<ContieneBean> contiene = cont.doRetrieveByKey(utente.getEmail());
					request.setAttribute("libri", contiene);
					
					System.out.println("Prima del dispatcher");
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Carrello.jsp");
					System.out.println("dopo il dispatcher");
					dispatcher.forward(request, response);					
				}
				
				if (action.equals("elimina") && !carrello.isEmpty()) {
					String libro_id = request.getParameter("libro_id");
					List<LibroBean> lista = carrello.getLibri();
					
					Iterator<LibroBean> it = lista.iterator();
					
					while (it.hasNext()) {
						LibroBean delete = it.next();
						if (delete.getId().equals(libro_id)) {
							lista.remove(delete);
							break;
						}
					}
					
					if (utente != null) {
						
						cont.doDeleteOne(libro_id, utente.getEmail());
						
					}
					if (carrello.isEmpty()) {
						response.setContentType("text/plain");
						response.getWriter().write("true");
						return;
					}
					else {
						response.setContentType("text/plain");
						response.getWriter().write("false");
						return;
					}
				}
				
				if (action.equals("insert") && azione == null) {
					
					
					String libro_id = request.getParameter("libro_id");
					LibroBean nuovo = (LibroBean) libro.doRetrieveByKey(libro_id);
					boolean controllo = false;
					
					List<LibroBean> lista = carrello.getLibri();
					
					
					Iterator<LibroBean> it = lista.iterator();
					
					while (it.hasNext()) {
						LibroBean contBean = it.next();
						
						if (nuovo.getId().equals(contBean.getId()))
						{controllo = true;}
					}
					
					if(!controllo)
					{
					carrello.addLibro(nuovo);
					
					
					System.out.println("Sono dopo l'inserimento");
					
					
					if (utente != null) {
						
						ContieneBean persistenza = new ContieneBean(utente.getEmail(),libro_id,nuovo.getPrezzo());
						cont.doSave(persistenza);
					}
					
					}
				}else if (azione != null) {
				if (azione.equals("elimina") && utente != null) {
					
					cont.doDeleteAll(utente.getEmail());
					
					response.sendRedirect("home.jsp");
					
				}
			}else if (utente == null) {
				System.out.println("\n\n\nHELLO\n\n\n");
				
				response.sendRedirect("Carrello.jsp");
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
