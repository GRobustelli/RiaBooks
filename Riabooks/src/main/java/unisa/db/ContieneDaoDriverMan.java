package unisa.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import unisa.ContieneBean;
import unisa.LibroBean;

public class ContieneDaoDriverMan implements IContieneDAO{
	
	private static final String TABLE_NAME = "Contiene";
	private DriverManagerConnectionPool dmcp = null;	

	public ContieneDaoDriverMan(DriverManagerConnectionPool dmcp) {
		
		this.dmcp = dmcp;
		System.out.println("DriverManager Product Model creation....");
	}

	@Override
	public synchronized void doSave(ContieneBean cont) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ContieneDaoDriverMan.TABLE_NAME
				+ " (email,libro_id, prezzo) VALUES (?, ?, ?)";
	
		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, cont.getEmail());
			preparedStatement.setString(2, cont.getLibro_id());
			preparedStatement.setFloat(3, cont.getPrezzo());
			preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
	}

	@Override
	public synchronized Collection<ContieneBean> doRetrieveByKey(String email) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ContieneBean> cont = new LinkedList<ContieneBean>();

		String selectSQL = "SELECT * FROM " + ContieneDaoDriverMan.TABLE_NAME + " WHERE email = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
		
			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ContieneBean bean = new ContieneBean();
				
				bean.setEmail(rs.getString("email"));
				bean.setLibro_id(rs.getString("libro_id"));
				bean.setPrezzo(rs.getFloat("prezzo"));
				cont.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return cont;
	}

	@Override
	public synchronized boolean doDeleteAll(String email) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
			
		int result = 0;
		
		String selectSQL = "DELETE FROM " + ContieneDaoDriverMan.TABLE_NAME + " WHERE email = ?";
			
		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			
			result = preparedStatement.executeUpdate();
			
		} 
		finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			dmcp.releaseConnection(connection);
		}
	}return (result != 0);
	}

	@Override
	public synchronized boolean doDeleteOne(String libro_id, String email) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
			
		int result = 0;
		
		String selectSQL = "DELETE FROM " + ContieneDaoDriverMan.TABLE_NAME + " WHERE libro_id = ? AND email = ?";
			
		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, libro_id);
			preparedStatement.setString(2, email);
			result = preparedStatement.executeUpdate();
			
		} 
		finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			dmcp.releaseConnection(connection);
		}
	}return (result != 0);
	}
	


}
