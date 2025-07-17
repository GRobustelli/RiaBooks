package unisa.db;

import java.sql.SQLException;
import java.util.Collection;

import unisa.RiferisceBean;



public interface IRiferisceDAO {
	
	public void doSave (RiferisceBean rif) throws SQLException;
	
	public Collection<RiferisceBean> doRetrieveByKey(int id) throws SQLException;
	
	public Collection<RiferisceBean> doRetrieveAll() throws SQLException;

}
