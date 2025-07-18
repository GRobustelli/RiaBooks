 package controllo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import unisa.Cart;
import unisa.ContieneBean;
import unisa.LibroBean;
import unisa.RiferisceBean;
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
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
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
			if (user!= null)
			{
				if (user.isAdmin()) {
					request.setAttribute("libri", libro.doRetrieveAllAdmin());
				}
				else {
					request.setAttribute("libri", libro.doRetrieveAll(null));
				}
			}else {
			request.setAttribute("libri", libro.doRetrieveAll(null));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}		
		
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		String action = request.getParameter("action");
		
		System.out.println("Siamo dopo aver preso action che ha valore: " + action);
		
		ArrayList<LibroBean> libriord = new ArrayList<>();
		System.out.println("\n\nSto nella servlet LibriControl prima degli if" + action);
		
		
		if(action != null)
		{
			
			
			if(action.equals("modCatalogo") && user.isAdmin()) {
				
				String libro_id = request.getParameter("libro_id");
				String valore = request.getParameter("valore");
				
				try {
					if (libro_id != null && valore != null) {
					
						
					if (valore.equals("false")) {
						
						if (libro.doUpdatemostra(libro_id,false)) {
							cont.doDeleteAllD(libro_id);} 							
					}
					else {
						if (libro.doUpdatemostra(libro_id,true)) {
							System.out.println("daglie roma daglie yahahahahooo");
						}
					}
						
					
					
					
					}else {
						System.out.println("Piangi e disperati");
					}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.setContentType("text/plain");
				response.getWriter().write("true");
				return;
			}
			
			if (action.equals("do_upload") && user.isAdmin()) {
				
				System.out.println("entrati nel caricamento dei libri");
				
				LibroBean bean = new LibroBean();
				RequestDispatcher dispatcherToInserisciPage = request.getRequestDispatcher("Admin/InserisciLibro.jsp");
				List<String> errors = new ArrayList<>();
				
				bean.setAutore(request.getParameter("autore"));
				bean.setCategoria(request.getParameter("categoria"));
				bean.setDescrizione(request.getParameter("descrizione"));
				bean.setId(request.getParameter("id"));
				
				String prezzo = request.getParameter("prezzo");
				
				if (prezzo == null || prezzo.trim().isEmpty()) {
					errors.add("Il campo prezzo non può essere vuoto");
				}
				else {
					bean.setPrezzo(Float.parseFloat(prezzo));}
				
				bean.setTitolo(request.getParameter("titolo"));
				
				if (bean.getAutore() == null || bean.getAutore().trim().isEmpty()) {
					errors.add("Il campo autore non può essere vuoto");
				}
				if (bean.getCategoria() == null || bean.getCategoria().trim().isEmpty()) {
					errors.add("Il campo categoria non può essere vuoto");
				}
				if (bean.getDescrizione()== null || bean.getDescrizione().trim().isEmpty()) {
					errors.add("Il campo descrizione non può essere vuoto");
				}
				if (bean.getId() == null || bean.getId().trim().isEmpty()) {
					errors.add("Il campo ID non può essere vuoto");}
				
				if (bean.getTitolo() == null || bean.getId().trim().isEmpty()) {
					errors.add("Il campo titolo non può essere vuoto");
				}
				
				try {
					LibroBean duplicate = (LibroBean) libro.doRetrieveByKey(bean.getId());
					if (duplicate != null) {
						
						System.out.println("Vediamo se è veramente non nullo: " + duplicate);
						if(duplicate.getId().equals(bean.getId())) {
						
						
						errors.add("Libro già esistente nel database");}
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				 if (!errors.isEmpty()) {
			        	request.setAttribute("errors", errors);
			        	dispatcherToInserisciPage.forward(request, response);
			        	return; 
			        }
				 
				 try {
					 
					libro.doSave(bean);
					//INSERIRE DISPATCHER VERSO IL SALVAMENTO DELL'IMMAGINE
					RequestDispatcher dispatcher = request.getRequestDispatcher("ImmUpdateCont");
					dispatcher.forward(request, response);
					
				 } catch (SQLException e) {
					
					e.printStackTrace();
				}
				
				 
				 
				}
				
			if (action.equals("ordini") && !user.isAdmin()) {
				ArrayList<Collection<RiferisceBean>> bigList = (ArrayList<Collection<RiferisceBean>>) request.getAttribute("bigList");
				Iterator<Collection<RiferisceBean>> it = bigList.iterator();
				while (it.hasNext()) {
					Collection<RiferisceBean> it2 = it.next();
					
					Iterator<RiferisceBean> it3 = it2.iterator();
					while(it3.hasNext()) {
						RiferisceBean rif = it3.next();
						try {
							libriord.add(libro.doRetrieveByKey(rif.getLibro_id()));
						} catch (SQLException e) {
							
							e.printStackTrace();
						}
						
					}
				}
				
				request.setAttribute("libri", libriord);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/RiepilogoOrdini.jsp");
				dispatcher.forward(request, response);
				
			}else if (action.equals("ordini") && user.isAdmin()) {
				
				System.out.println("Sono in libri control ordini admin");
				
				try {
					Collection<LibroBean> libriad = (Collection<LibroBean>) libro.doRetrieveAllAdmin();
					request.setAttribute("libriad", libriad);
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				getServletContext().getRequestDispatcher("/Admin/adminriepilogoordini.jsp").forward(request, response);
			}
			
		}
		else {
			
		if (cart == null)
		{
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}else if (user != null && cart.isEmpty()) 
		
		{
			
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
						ContieneBean save = new ContieneBean(user.getEmail(),bean.getId(),bean.getPrezzo());
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
		if(user != null ) { System.out.println("\nSto prima del controllo di admin\n" + user.isAdmin());
			
		if (user.isAdmin()) {	
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Admin/AdminHome.jsp");
			dispatcher.forward(request, response);
			return;
		}
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
		dispatcher.forward(request, response);
		return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("id");
		
		System.out.println("Siamo nel post id ha valore: " + action);
		doGet(request, response);
	}

}
