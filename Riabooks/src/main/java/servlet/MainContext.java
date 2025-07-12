package servlet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import unisa.db.DriverManagerConnectionPool;

import javax.sql.DataSource;



@WebListener
public class MainContext implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		// Per usare il DriverManager
		DriverManagerConnectionPool dm = new DriverManagerConnectionPool();
		context.setAttribute("DriverManager", dm);
		System.out.println("DriverManager creation...."+dm.toString());		
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}

}
