package unisa.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ImmagineDaoDriverMan implements IImmagineDAO{

	private DriverManagerConnectionPool dmcp = null;	

	public ImmagineDaoDriverMan(DriverManagerConnectionPool dmcp) {
		
		this.dmcp = dmcp;
		System.out.println("DriverManager Product Model creation....");
		
	}
	
	
	@Override
	public synchronized byte[] doRetrieve(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		byte[] bt = null;
		
		try {
			connection = dmcp.getConnection();
			String sql = "SELECT immagine FROM libro WHERE id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, id);
			
			rs = stmt.executeQuery();
			if (rs.next()) {
				bt = rs.getBytes("immagine");
			}
		} catch (SQLException sqlException) {
			System.out.println(sqlException);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				System.out.println(sqlException);
			} 
			finally {
				dmcp.releaseConnection(connection);
			}
		}
		
		return bt;
		
	}

	@Override
	public synchronized void updateImm(String id, InputStream imm) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		
		String sql = "UPDATE libro SET immagine = ? WHERE id = ?";
		
		try {
			con = dmcp.getConnection();
			stmt = con.prepareStatement(sql);
			
			stmt.setBinaryStream(1,imm,imm.available());
			stmt.setString(2, id);
			
			stmt.executeUpdate();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
		finally {
		try {
			if (stmt != null)
				stmt.close();
		} finally {
			dmcp.releaseConnection(con);
		}
	}
		
	}

}
