package unisa.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import unisa.UserBean;

public class UserDaoDriverMan implements IUserDAO {

	private static final String TABLE_NAME = "Utente";
	private DriverManagerConnectionPool dmcp = null;	

	public UserDaoDriverMan(DriverManagerConnectionPool dmcp) {
		
		this.dmcp = dmcp;
		System.out.println("DriverManager Product Model creation....user");
	}
	
	
	@Override
	public synchronized void doSave(UserBean utente) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement carstatement = null;
		
		String insertSQL = "INSERT INTO " + UserDaoDriverMan.TABLE_NAME
				+ " (email,nome,cognome,pass_hash,is_admin) VALUES (?, ?, ?, ?, ?)";
		
		String insertcartSQLString = "INSERT INTO Carrello (email) VALUES (?)"; 

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, utente.getEmail());
			preparedStatement.setString(2, utente.getNome());
			preparedStatement.setString(3, utente.getCognome());
			preparedStatement.setString(4, utente.getPass());
			preparedStatement.setBoolean(5, utente.isAdmin());

			preparedStatement.executeUpdate();
			carstatement = connection.prepareStatement(insertcartSQLString);
		
			carstatement.setString(1, utente.getEmail());
			carstatement.executeUpdate();
		} finally {
			try {
				
				if (preparedStatement != null)
					preparedStatement.close();
				
				if (carstatement != null)
					carstatement.close();
				
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
	}

	
	public synchronized UserBean doRetrieveByKey(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		UserBean bean = new UserBean();

		String selectSQL = "SELECT * FROM " + UserDaoDriverMan.TABLE_NAME + " WHERE email = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setEmail(rs.getNString("email"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setPass(rs.getString("pass_hash"));
				bean.setAdmin(rs.getBoolean("is_admin"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return bean;
	}

	@Override
	public synchronized Collection<UserBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<UserBean> products = new LinkedList<UserBean>();

		String selectSQL = "SELECT * FROM " + UserDaoDriverMan.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				UserBean bean = new UserBean();

				bean.setEmail(rs.getString("email"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setPass(rs.getString("pass_hash"));
				bean.setAdmin(rs.getBoolean("is_admin"));
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return products;
	}
}
