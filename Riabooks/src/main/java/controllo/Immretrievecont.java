package controllo;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.db.DriverManagerConnectionPool;
import unisa.db.IImmagineDAO;
import unisa.db.ImmagineDaoDriverMan;

import java.io.IOException;
import java.sql.SQLException;


/**
 * Servlet implementation class Immretrievecont
 */
@WebServlet("/Immretrievecont")
public class Immretrievecont extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Immretrievecont() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DriverManagerConnectionPool dm = (DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager");
		IImmagineDAO dao = new ImmagineDaoDriverMan(dm);
		
		
		String id = (String) request.getParameter("id");
		if (id != null) 
		{
			byte[] bt;
			try {
				bt = dao.doRetrieve(id);
				ServletOutputStream out = response.getOutputStream();
				if (bt != null) 
				{
					out.write(bt);
					response.setContentType("image/jpeg");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
