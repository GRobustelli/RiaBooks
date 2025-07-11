package unisa.db;

import java.sql.SQLException;
import java.util.Collection;
import unisa.RecensioneBean;

public interface IRecensioneDAO {
	
	
		
		public void doSave (RecensioneBean rec) throws SQLException;
		
		public RecensioneBean RetrieveByKey(int id) throws SQLException;
		
		public boolean doDelete (int codice) throws SQLException;
		
		public Collection<RecensioneBean> doRetrieveAll(String order) throws SQLException;

	

	
}
