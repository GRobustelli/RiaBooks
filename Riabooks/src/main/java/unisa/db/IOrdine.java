package unisa.db;

import java.sql.SQLException;
import java.util.Collection;

import unisa.OrdineBean;
import unisa.RecensioneBean;

public interface IOrdine {
	
	public void doSave (OrdineBean ord) throws SQLException;
	
	public OrdineBean doRetrieveByKey(int id) throws SQLException;
	
	public boolean doDelete (int id) throws SQLException;
	
	public Collection<OrdineBean> doRetrieveAll(String order) throws SQLException;
	
	public Collection<OrdineBean> doRetrieveAllUser (String email) throws SQLException;

}
