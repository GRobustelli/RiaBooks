
import java.sql.SQLException;
import java.util.LinkedList;

import unisa.LibroBean;
import unisa.OrdineBean;
import unisa.RecensioneBean;
import unisa.UserBean;
import unisa.db.DriverManagerConnectionPool;
import unisa.db.ILibroDAO;
import unisa.db.IOrdineDAO;
import unisa.db.IRecensioneDAO;
import unisa.db.IUserDAO;
import unisa.db.LibroDaoDriverMan;
import unisa.db.OrdineDaoDriverMan;
import unisa.db.RecensioneDaoDriverMan;
import unisa.db.UserDaoDriverMan;

public class provautil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DriverManagerConnectionPool dm = new DriverManagerConnectionPool();
		System.out.println("DriverManager creation...."+dm.toString());	
		
		IUserDAO um = new UserDaoDriverMan(dm);
		
		System.out.println("Stiamo prime del try");
		
		try {
			
	
			UserBean rs = (UserBean) um.doRetrieveByKey("miaail@hotmail.com");
			
			if (rs == null) {
				System.out.println("Esce null dai");
			}
			else {
			System.out.println(rs.getNome() + " "+ rs.getEmail());
			dm.releaseConnection(null);}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n\n********************************************\nproviamo");
		
		ILibroDAO libro = new LibroDaoDriverMan(dm);
		System.out.println("DriverManager creation...."+dm.toString());	
		IRecensioneDAO rec = new RecensioneDaoDriverMan(dm);
		IOrdineDAO ord = new OrdineDaoDriverMan(dm);
		
		try {
			LibroBean l = new LibroBean();
			l.setId("1A");
			l.setAutore("Giovanni");
			l.setCategoria("Fantasy");
			l.setTitolo("Non voglio Studiare");
			l.setDescrizione("Un libro che ti porta nel mondo fantastico di uno studente disperato");
			l.setPrezzo(100.12);
			boolean control = false;
			
			LibroBean l2 = new LibroBean();
			LinkedList<LibroBean> set = (LinkedList<LibroBean>)libro.doRetrieveAll(null); 
			for (LibroBean i: set) {
				System.out.println(i.getId() + "\n\n" + l.getId());
				if(l.getId().equals(i.getId())) 
				{
					control = true;
					break;}
		
			}
			
			if (!control) {
				libro.doSave(l);
			}
			
			
			l2 = (LibroBean)libro.doRetrieveByKey("1A");
			System.out.println(l2.getId() + " "+ l2.getAutore() + " \n" + l2.getDescrizione());
		   
			RecensioneBean nuova = new RecensioneBean();
			
			nuova.setEmail("miaemail@hotmail.com");
			nuova.setLibro_id("1A");
			nuova.setTesto("Deh bellissimo, mi sento uguale al protagonista");
			nuova.setValutazione(10);
			
			rec.doSave(nuova);
			
			RecensioneBean nuova2 = (RecensioneBean)rec.RetrieveByKey(2);
			
			System.out.println(nuova2.getEmail() + " \n" + nuova2.getId() + " \n"+ nuova2.getLibro_id() + " \n" + nuova2.getTesto());
			
			
			
			System.out.println("\n\n********************************************\n");
			
			OrdineBean o = new OrdineBean();
			
			o.setEmail("admin@hotmail.com");
			o.setId(1);
			o.setMetodo("53433134135235");
			o.setImporto(l2.getPrezzo());
			
			//ord.doSave(o);
			
			LinkedList<OrdineBean> o2 = (LinkedList<OrdineBean>)ord.doRetrieveAllUser(o.getEmail());
			
			for (OrdineBean i : o2)
			{
				System.out.println(i.getEmail() + " " + i.getId() + " " + i.getImporto() + "\n\n\n");
			}
			 
			
			
			
			dm.releaseConnection(null);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
