package unisa.db;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;

import unisa.OrdineBean;
import unisa.RecensioneBean;

public interface IOrdineDAO {
	
	public void doSave (OrdineBean ord) throws SQLException;
	
	public OrdineBean doRetrieveByKey(int id) throws SQLException;
	
	public boolean doDelete (int id) throws SQLException;
	
	public Collection<OrdineBean> doRetrieveAll(String order) throws SQLException;
	
	public Collection<OrdineBean> doRetrieveDate(Date start, Date finish) throws SQLException;
	
	public Collection<OrdineBean> doRetrieveAllByEmail() throws SQLException; 
	
	public Collection<OrdineBean> doRetrieveAllUser (String email) throws SQLException;

}
