package unisa.db;

import java.io.InputStream;
import java.sql.SQLException;

public interface IImmagineDAO {
	
	public byte[] doRetrieve(String id) throws SQLException;
	
	public void updateImm (String id, InputStream imm) throws SQLException;
	
}
