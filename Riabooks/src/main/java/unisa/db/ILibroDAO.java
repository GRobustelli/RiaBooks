package unisa.db;

import java.sql.SQLException;
import java.util.Collection;

import unisa.LibroBean;
import unisa.UserBean;

public interface ILibroDAO {
	
	public void doSave (LibroBean libro) throws SQLException;
	
	public LibroBean doRetrieveByKey(String id) throws SQLException;
	
	public boolean doDelete (String codice) throws SQLException;
	
	public boolean doUpdate(String codice, float prezzo) throws SQLException;
	public boolean doUpdate(String codice, String modifica, String update) throws SQLException;
	public boolean doUpdatemostra(String codice, boolean update) throws SQLException;
	public Collection<LibroBean> doRetrieveAll(String order) throws SQLException;

	public Collection<LibroBean> doRetrieveAllAdmin() throws SQLException;

}
