package unisa.db;

import java.sql.SQLException;
import java.util.Collection;

public interface IUserDAO {
	
	public void doSave (UserBean user) throws SQLException;
	
	public UserBean doretrievebyEmail (String email) throws SQLException;
	
	public Collection<UserBean> doRetrieveAll(String order) throws SQLException;

}
