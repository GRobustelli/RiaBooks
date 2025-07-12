package unisa.db;

import java.sql.SQLException;
import java.util.Collection;

import unisa.ContieneBean;

public interface IContieneDAO {

	public void doSave (ContieneBean libro) throws SQLException;
	
	public Collection<ContieneBean> doRetrieveByKey (String email) throws SQLException;
	
	public boolean doDeleteAll (String email) throws SQLException;
	
	public boolean doDeleteOne (String libro_id) throws SQLException;
	
	
}
