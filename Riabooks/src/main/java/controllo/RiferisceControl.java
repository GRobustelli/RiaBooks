package controllo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.Cart;
import unisa.LibroBean;
import unisa.OrdineBean;
import unisa.RiferisceBean;
import unisa.UserBean;
import unisa.db.DriverManagerConnectionPool;
import unisa.db.IRiferisceDAO;
import unisa.db.RiferisceDaoDriverMan;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Servlet implementation class RiferisceControl
 */
@WebServlet("/RiferisceControl")
public class RiferisceControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RiferisceControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		DriverManagerConnectionPool dm = (DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager");
		IRiferisceDAO rif = new RiferisceDaoDriverMan(dm);
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		String action = request.getParameter("action");
		OrdineBean ordBean = (OrdineBean) request.getAttribute("ordBean");
		
		
		if (action != null) {
			try {
				if (action.equals("insert") && !cart.isEmpty() && ordBean != null) {
					List<LibroBean> lista = cart.getLibri();
					
					Iterator<LibroBean> it = lista.iterator();
					
					while(it.hasNext()) {
						LibroBean libro = it.next();
						RiferisceBean rifBean = new RiferisceBean();
						rifBean.setOrdine_id(ordBean.getId());
						String conc = "q_" +  libro.getId();
						
						String quantita = request.getParameter(conc);
						
						rifBean.setLibro_id(libro.getId());
						rifBean.setQuantita(Integer.parseInt(quantita));
						
						rif.doSave(rifBean);
						
					}
					cart.deleteAll();
					
					request.setAttribute("azione", "elimina");
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CartControl");
					dispatcher.forward(request, response);
					
				}
				if (action.equals("ordini")) {
					
			
					UserBean user = (UserBean) request.getSession().getAttribute("user");
					if (user != null && user.isAdmin()) {
						//fai qualcosa di bello
					}
					else if (user != null) {
						Collection<OrdineBean> ordColl = (Collection<OrdineBean>)request.getAttribute("collOrd");
						Iterator<OrdineBean> it = ordColl.iterator();
						ArrayList<Collection<RiferisceBean>> bigList = new ArrayList<>();
						
						while (it.hasNext())
						{
							OrdineBean ord = it.next();
							
							Collection<RiferisceBean> riferimenti = rif.doRetrieveByKey(ord.getId());
							
							bigList.add(riferimenti);
							
						}
						
						request.setAttribute("bigList", bigList);
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/LibriControl");
						dispatcher.forward(request, response);
					}
				}	
			}catch (SQLException e) {
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
