package unisa.db;

import java.sql.SQLException;
import java.util.Collection;

import unisa.UserBean;

public interface IUserDAO {
	
	public void doSave (UserBean user) throws SQLException;
	
	public UserBean doRetrieveByKey(String email) throws SQLException;
	
	public Collection<UserBean> doRetrieveAll(String order) throws SQLException;



}
