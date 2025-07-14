package unisa.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import unisa.LibroBean;
import unisa.RiferisceBean;

public class RiferisceDaoDriverMan implements IRiferisceDAO{

	private static final String TABLE_NAME = "riferisce";
	private DriverManagerConnectionPool dmcp = null;	

	public RiferisceDaoDriverMan(DriverManagerConnectionPool dmcp) {
		
		this.dmcp = dmcp;
		System.out.println("DriverManager Product Model creation....RiferisceDaoDriverMan");

	}
	
	
	@Override
	public void doSave(RiferisceBean rif) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + RiferisceDaoDriverMan.TABLE_NAME
				+ " (ordine_id, libro_id, quantita) VALUES (?, ?, ?)";
	
		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, rif.getOrdine_id());
			preparedStatement.setString(2, rif.getLibro_id());
			preparedStatement.setInt(3, rif.getQuantita());

			
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
	public Collection<RiferisceBean> doRetrieveByKey(int id) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<RiferisceBean> rif = new  LinkedList<RiferisceBean>();

		String selectSQL = "SELECT * FROM " + RiferisceDaoDriverMan.TABLE_NAME + " WHERE ordine_id = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				RiferisceBean bean = new RiferisceBean();
				bean.setOrdine_id(rs.getInt("ordine_id"));
				bean.setLibro_id(rs.getString("libro_id"));
				bean.setQuantita(rs.getInt("quantita"));
				rif.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return rif;
	}

}
