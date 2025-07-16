package unisa.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import unisa.OrdineBean;
import unisa.UserBean;

public class OrdineDaoDriverMan implements IOrdineDAO{


	private static final String TABLE_NAME = "Ordine";
	private DriverManagerConnectionPool dmcp = null;	

	public OrdineDaoDriverMan(DriverManagerConnectionPool dmcp) {
		
		this.dmcp = dmcp;
		System.out.println("DriverManager Product Model creation....Ordine");
	}
	
	@Override
	public void doSave(OrdineBean ord) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + OrdineDaoDriverMan.TABLE_NAME
				+ " (importo,metodo,indirizzo,email,data_emissione) VALUES (?, ?, ?, ?, ?)";
	
		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setFloat(1, ord.getImporto());
			preparedStatement.setString(2, ord.getMetodo());
			preparedStatement.setString(3, ord.getIndirizzo());
			preparedStatement.setString(4, ord.getEmail());
			preparedStatement.setDate(5, ord.getData());
			
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
				bean.setImporto(rs.getFloat("importo"));
				bean.setEmail(rs.getString("email"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setMetodo(rs.getString("metodo"));
				bean.setData(rs.getDate("data_emissione"));
				
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
				bean.setImporto(rs.getFloat("importo"));
				bean.setEmail(rs.getString("email"));
				bean.setMetodo(rs.getString("metodo"));
				bean.setData(rs.getDate("data_emissione"));
				bean.setIndirizzo(rs.getString("indirizzo"));
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
			System.out.println("\n\nSto prima di execute in doretrievealluser\n\n");
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("\n\nSto dopo di execute in doretrievealluser\n\n");
			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				
				bean.setId(rs.getInt("id"));
				bean.setImporto(rs.getFloat("importo"));
				bean.setEmail(rs.getString("email"));
				bean.setMetodo(rs.getString("metodo"));
				bean.setData(rs.getDate("data_emissione"));
				bean.setIndirizzo(rs.getString("indirizzo"));
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
	public Collection<OrdineBean> doRetrieveAllByEmail() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdineBean> ordini = new LinkedList<OrdineBean>();

		String selectSQL = "SELECT * FROM " + OrdineDaoDriverMan.TABLE_NAME + "ORDER BY = ?";
		try {
			
			connection = dmcp.getConnection();
			
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, "email");
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				
				bean.setId(rs.getInt("id"));
				bean.setImporto(rs.getFloat("importo"));
				bean.setEmail(rs.getString("email"));
				bean.setMetodo(rs.getString("metodo"));
				bean.setData(rs.getDate("data_emissione"));
				bean.setIndirizzo(rs.getString("indirizzo"));
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
	public Collection<OrdineBean> doRetrieveDate(Date start, Date finish) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdineBean> ordini = new LinkedList<OrdineBean>();

		String selectSQL = "SELECT * FROM " + OrdineDaoDriverMan.TABLE_NAME + " WHERE data_emissione >= ? AND data_emissione <= ?";
			
		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setDate(1, start);
			preparedStatement.setDate(2, finish);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				
				bean.setId(rs.getInt("id"));
				bean.setImporto(rs.getFloat("importo"));
				bean.setEmail(rs.getString("email"));
				bean.setMetodo(rs.getString("metodo"));
				bean.setData(rs.getDate("data_emissione"));
				bean.setIndirizzo(rs.getString("indirizzo"));
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
	public OrdineBean doRetrieveLast(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		OrdineBean bean = new OrdineBean();

		String selectSQL = "SELECT * FROM " + OrdineDaoDriverMan.TABLE_NAME +  " WHERE email = ? ORDER BY id DESC LIMIT 1";
		
try {
			
			connection = dmcp.getConnection();
			
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			
			System.out.println("stiamo prima di execute in ordinedao");
			
			ResultSet rs = preparedStatement.executeQuery();
			
			rs.next();
			
			
			
			bean.setId(rs.getInt("id"));
			bean.setImporto(rs.getFloat("importo"));
			bean.setEmail(rs.getString("email"));
			bean.setMetodo(rs.getString("metodo"));
			bean.setData(rs.getDate("data_emissione"));
			bean.setIndirizzo(rs.getString("indirizzo"));
			
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

}
