package controllo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import unisa.OrdineBean;
import unisa.UserBean;
import unisa.db.DriverManagerConnectionPool;
import unisa.db.IOrdineDAO;
import unisa.db.OrdineDaoDriverMan;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import com.mysql.cj.protocol.a.LocalDateTimeValueEncoder;

/**
 * Servlet implementation class OrdiniControl
 */
@WebServlet("/OrdiniControl")
public class OrdiniControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdiniControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("\n\nSto nella servlet OrdiniControl");
		
		DriverManagerConnectionPool dm = (DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager");
		
		IOrdineDAO ord = new OrdineDaoDriverMan(dm);
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		
		String action = request.getParameter("action");
		
		if (action != null)
		{
			try {
				if (action.equals("insert")) {
					String imptot = request.getParameter("impTot");
					String metodo = request.getParameter("numeroCarta");
					String indirizzo = request.getParameter("indirizzo");
					LocalDate dataOggi = LocalDate.now();
					Date dataSQL = Date.valueOf(dataOggi);
					
					OrdineBean bean = new OrdineBean();
					
					if (user != null) {
						bean.setImporto(Float.parseFloat(imptot));
						bean.setMetodo(metodo);
						bean.setIndirizzo(indirizzo);
						bean.setEmail(user.getEmail());
						bean.setData(dataSQL);
						
						System.out.println("PRima di inserimento ordine");
						
						ord.doSave(bean);
						
						System.out.println("Dopo di inserimento ordine");
						
						OrdineBean ordBean = (OrdineBean) ord.doRetrieveLast(user.getEmail());
						request.setAttribute("ordBean", ordBean);
						
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/RiferisceControl");
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
