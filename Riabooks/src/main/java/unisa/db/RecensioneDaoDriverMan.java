package unisa.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import unisa.LibroBean;
import unisa.RecensioneBean;

public class RecensioneDaoDriverMan implements IRecensioneDAO{

	private static final String TABLE_NAME = "Recensione";
	private DriverManagerConnectionPool dmcp = null;	

	public RecensioneDaoDriverMan(DriverManagerConnectionPool dmcp) {
		
		this.dmcp = dmcp;
		System.out.println("DriverManager Product Model creation....");
		
	}
	@Override
	public void doSave(RecensioneBean rec) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + RecensioneDaoDriverMan.TABLE_NAME
				+ " (testo,valutazione,email,libro_id) VALUES (?, ?, ?, ?)";
	
		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, rec.getTesto());
			preparedStatement.setInt(2, rec.getValutazione());
			preparedStatement.setString(3, rec.getEmail());
			preparedStatement.setString(4, rec.getLibro_id());
			
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
	public RecensioneBean RetrieveByKey(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		RecensioneBean bean = new RecensioneBean();

		String selectSQL = "SELECT * FROM " + RecensioneDaoDriverMan.TABLE_NAME + " WHERE id = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
			bean.setId(rs.getInt("id"));
			bean.setEmail(rs.getString("email"));
			bean.setLibro_id(rs.getString("Libro_id"));
			bean.setTesto(rs.getString("testo"));
			bean.setValutazione(rs.getInt("valutazione"));
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
	public boolean doDelete(int codice) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
			
		int result = 0;
		
		String selectSQL = "DELETE FROM " + RecensioneDaoDriverMan.TABLE_NAME + " WHERE id = ?";
			
		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, codice);
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
	public Collection<RecensioneBean> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<RecensioneBean> recRecord = new LinkedList<RecensioneBean>();

		String selectSQL = "SELECT * FROM " + RecensioneDaoDriverMan.TABLE_NAME;

		if (order != null && !order.equals("")) {
			
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				RecensioneBean bean = new RecensioneBean();
				
				bean.setId(rs.getInt("id"));
				bean.setEmail(rs.getString("email"));
				bean.setLibro_id(rs.getString("Libro_id"));
				bean.setTesto(rs.getString("testo"));
				bean.setValutazione(rs.getInt("valutazione"));
				
				recRecord.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return recRecord;
	}
	

}
