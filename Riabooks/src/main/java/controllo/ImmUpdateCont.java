package controllo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import unisa.db.DriverManagerConnectionPool;
import unisa.db.IImmagineDAO;
import unisa.db.ImmagineDaoDriverMan;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class ImmUpdateCont
 */
@WebServlet("/ImmUpdateCont")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ImmUpdateCont extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImmUpdateCont() {
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
		// TODO Auto-generated method stub
		DriverManagerConnectionPool dm = (DriverManagerConnectionPool) getServletContext().getAttribute("DriverManager");
		
		System.out.println("\n\nStiamo nella post di immupdatecont\n\n");
		
		IImmagineDAO dao = new ImmagineDaoDriverMan(dm);
		
		String modaction = request.getParameter("modaction");
		
		if (modaction != null) {
			
			if (modaction.equals("do_mod")) {
				String libro_id = request.getParameter("libro_id");
				
				for (Part part : request.getParts()) {
				
					String fileName = part.getSubmittedFileName();
					if (fileName != null && !fileName.equals("")) {
						try {
						
							dao.updateImm(libro_id, part.getInputStream());
							
						}catch (SQLException sqlException) {
							System.out.println(sqlException);
							}
						}

					}	
				response.sendRedirect(request.getContextPath() + "/Admin/AdminHome.jsp");
				return;
			}			
		}
		
		
		String libro_id = request.getParameter("id");
		
		for (Part part : request.getParts()) {
		
			String fileName = part.getSubmittedFileName();
			if (fileName != null && !fileName.equals("")) {
				try {
				
					dao.updateImm(libro_id, part.getInputStream());
					
				}catch (SQLException sqlException) {
					System.out.println(sqlException);
		}
		
	}

}	
		response.sendRedirect("Admin/InserisciLibro.jsp");
		return;
		}
	

}
