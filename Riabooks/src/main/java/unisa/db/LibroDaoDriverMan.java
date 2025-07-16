package unisa.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import unisa.LibroBean;

public class LibroDaoDriverMan implements ILibroDAO{

	private static final String TABLE_NAME = "Libro";
	private DriverManagerConnectionPool dmcp = null;	

	public LibroDaoDriverMan(DriverManagerConnectionPool dmcp) {
		
		this.dmcp = dmcp;
		System.out.println("DriverManager Product Model creation LibroDaoDriverMan");
		
	}
	
	
	public synchronized void doSave(LibroBean libro) throws SQLException {
		// TODO Auto-generated method stub

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + LibroDaoDriverMan.TABLE_NAME
				+ " (id, titolo, autore, prezzo, descrizione, categoria) VALUES (?, ?, ?, ?, ?, ?)";
	
		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, libro.getId());
			preparedStatement.setString(2, libro.getTitolo());
			preparedStatement.setString(3, libro.getAutore());
			preparedStatement.setDouble(4, libro.getPrezzo());
			preparedStatement.setString(5,libro.getDescrizione());
			preparedStatement.setString(6, libro.getCategoria());

			
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
	public synchronized LibroBean doRetrieveByKey(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		LibroBean bean = new LibroBean();

		String selectSQL = "SELECT * FROM " + LibroDaoDriverMan.TABLE_NAME + " WHERE id = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getString("id"));
				bean.setTitolo(rs.getString("titolo"));
				bean.setAutore(rs.getString("autore"));
				bean.setPrezzo(rs.getFloat("prezzo"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setCategoria(rs.getString("categoria"));
				bean.setImmagine(rs.getString("immagine"));
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
	public synchronized boolean doDelete(String codice) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
			
		int result = 0;
		
		String selectSQL = "DELETE FROM " + LibroDaoDriverMan.TABLE_NAME + " WHERE id = ?";
			
		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, codice);
			
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
	public synchronized Collection<LibroBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<LibroBean> libri = new LinkedList<LibroBean>();

		String selectSQL = "SELECT * FROM " + LibroDaoDriverMan.TABLE_NAME;

		if (order != null && !order.equals("")) {
			
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				LibroBean bean = new LibroBean();
				
				bean.setId(rs.getString("id"));
				bean.setTitolo(rs.getString("titolo"));
				bean.setAutore(rs.getString("autore"));
				bean.setPrezzo(rs.getFloat("prezzo"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setCategoria(rs.getString("categoria"));
				bean.setImmagine(rs.getString("immagine"));
				libri.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return libri;
	}


	@Override
	public synchronized boolean doUpdate(String codice, double prezzo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
			
		int result = 0;
		
		String selectSQL = "UPDATE Libro SET prezzo = "+ prezzo + "FROM " + LibroDaoDriverMan.TABLE_NAME + " WHERE id = ?";
			
		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, codice);
			
			result = preparedStatement.executeUpdate();
			
		} finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} 
		finally {
			dmcp.releaseConnection(connection);
		}
		
	}
		return (result != 0);
		}


	@Override
	public synchronized boolean doUpdate(String codice, String modifica, String update) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
			
		int result = 0;
		
		String selectSQL = "UPDATE Libro SET " + modifica + "= "+ update + "FROM " + LibroDaoDriverMan.TABLE_NAME + " WHERE id = ?";
			
		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, codice);
			
			result = preparedStatement.executeUpdate();
			
		} finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} 
		finally {
			dmcp.releaseConnection(connection);
		}
		
	}
		return (result != 0);
		
	}
	}


