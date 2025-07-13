package controllo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import unisa.Cart;
import unisa.ContieneBean;
import unisa.LibroBean;
import unisa.UserBean;
import unisa.db.ContieneDaoDriverMan;
import unisa.db.DriverManagerConnectionPool;
import unisa.db.IContieneDAO;
import unisa.db.ILibroDAO;
import unisa.db.LibroDaoDriverMan;
/**
 * Servlet implementation class LibriControl
 */
@WebServlet("/LibriControl")
public class LibriControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibriControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("\n\nSto nella servlet LibriControl");
		
		DriverManagerConnectionPool dm = (DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager");
		ILibroDAO libro = new LibroDaoDriverMan(dm);
		IContieneDAO cont = new ContieneDaoDriverMan(dm);
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		
		try {
			request.setAttribute("libri", libro.doRetrieveAll(null));
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}		
		
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		
		System.out.println("\n\nSto nella servlet LibriControl prima degli if");
		
		if (cart == null)
		{
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		else if (user != null && cart.isEmpty()) 
		{
			System.out.println("\n\nSiamo entrati daglie");
				try {
				
					Collection<ContieneBean> libri =  cont.doRetrieveByKey(user.getEmail());
					Iterator<ContieneBean> it = libri.iterator();
					
					while (it.hasNext()) {
						ContieneBean bean =  it.next();
						cart.addLibro(libro.doRetrieveByKey(bean.getLibro_id()));
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}else {
		
			try {
				if(user != null) 
				{
					System.out.println("non va bene forza napoli");
				Collection<ContieneBean> libri =  cont.doRetrieveByKey(user.getEmail());
				List<LibroBean> lista = cart.getLibri();
				
				Iterator<LibroBean> itl = lista.iterator();
				Iterator<ContieneBean> it;
				boolean control = false;
				
				
				while (itl.hasNext()) {
					LibroBean bean = itl.next();
					control = false;
					it = libri.iterator();
					
					while (it.hasNext()) {
					ContieneBean conbean = it.next();
					if (conbean.getLibro_id().equals(bean.getId())) {
						control = true;
						}}
					if (!control) {
						ContieneBean save = new ContieneBean(user.getEmail(),bean.getId());
						cont.doSave(save);
					}
					
				}
				
				itl = lista.iterator();
				 it = libri.iterator();
				 
				 while (it.hasNext()) {
					 ContieneBean conbean = it.next();
					 control = false;
					 itl = lista.iterator();
					while (itl.hasNext()){
						LibroBean bean = itl.next();
						if (bean.getId().equals(conbean.getLibro_id()))
							control = true;
						}
					if (!control) {
						cart.addLibro(libro.doRetrieveByKey(conbean.getLibro_id()));
					}
					 
				 }
						
			}} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
