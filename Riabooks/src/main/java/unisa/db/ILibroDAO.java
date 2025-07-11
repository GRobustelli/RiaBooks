package unisa.db;

import java.sql.SQLException;
import java.util.Collection;

import unisa.LibroBean;
import unisa.UserBean;

public interface ILibroDAO {
	
	public void doSave (LibroBean libro) throws SQLException;
	
	public LibroBean RetrieveByKey(String id) throws SQLException;
	
	public boolean doDelete (String codice) throws SQLException;
	
	public Collection<LibroBean> doRetrieveAll(String order) throws SQLException;



}
