package unisa.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import unisa.OrdineBean;

public class OrdineDaoDriverMan implements IOrdineDAO{


	private static final String TABLE_NAME = "Ordine";
	private DriverManagerConnectionPool dmcp = null;	

	public OrdineDaoDriverMan(DriverManagerConnectionPool dmcp) {
		
		this.dmcp = dmcp;
		System.out.println("DriverManager Product Model creation....");
	}
	
	@Override
	public void doSave(OrdineBean ord) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + OrdineDaoDriverMan.TABLE_NAME
				+ " (id,importo,metodo,email) VALUES (?, ?, ?, ?)";
	
		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, ord.getId());
			preparedStatement.setDouble(2, ord.getImporto());
			preparedStatement.setString(3, ord.getMetodo());
			preparedStatement.setString(4, ord.getEmail());
			
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
	public OrdineBean doRetrieveByKey(int id) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		OrdineBean bean = new OrdineBean();

		String selectSQL = "SELECT * FROM " + OrdineDaoDriverMan.TABLE_NAME + " WHERE id = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt("id"));
				bean.setImporto(rs.getDouble("importo"));
				bean.setEmail(rs.getString("email"));
				bean.setMetodo(rs.getString("metodo"));
				
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
	public boolean doDelete(int id) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
			
		int result = 0;
		
		String selectSQL = "DELETE FROM " + OrdineDaoDriverMan.TABLE_NAME + " WHERE id = ?";
			
		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);
			
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
	public Collection<OrdineBean> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdineBean> ordini = new LinkedList<OrdineBean>();

		String selectSQL = "SELECT * FROM " + OrdineDaoDriverMan.TABLE_NAME;

		if (order != null && !order.equals("")) {
			
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				
				bean.setId(rs.getInt("id"));
				bean.setImporto(rs.getDouble("importo"));
				bean.setEmail(rs.getString("email"));
				bean.setMetodo(rs.getString("metodo"));
				
				ordini.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return ordini;
	}

	@Override
	public Collection<OrdineBean> doRetrieveAllUser(String email) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdineBean> ordini = new LinkedList<OrdineBean>();

		String selectSQL = "SELECT * FROM " + OrdineDaoDriverMan.TABLE_NAME + " WHERE email = ?";
			
		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				
				bean.setId(rs.getInt("id"));
				bean.setImporto(rs.getDouble("importo"));
				bean.setEmail(rs.getString("email"));
				bean.setMetodo(rs.getString("metodo"));
				
				ordini.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return ordini;
	}

}
