package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AziendeDAO {
	    private String jdbcURL;
	    private String jdbcUsername;
	    private String jdbcPassword;
	    private Connection con;
	     
	    public AziendeDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
	        this.jdbcURL = jdbcURL;
	        this.jdbcUsername = jdbcUsername;
	        this.jdbcPassword = jdbcPassword;
	    }
	     
	    protected void connect() throws SQLException {
	        if (con == null || con.isClosed()) {
	            try {
	                Class.forName("com.mysql.jdbc.Driver");
	            } catch (ClassNotFoundException e) {
	                throw new SQLException(e);
	            }
	            con = DriverManager.getConnection(
	            		"jdbc:mariadb://localhost:3306/iti_elia_5f_as_2020_21_contabilita",
						"root", ""
						);
	        }
	    }
	     
	    protected void disconnect() throws SQLException {
	        if (con != null && !con.isClosed()) {
	            con.close();
	        }
	    }
	    
	    public boolean insertAziende(Aziende aziende) throws SQLException {
	        String sql = "INSERT INTO aziende (anagrafica, userId,passwd ) VALUES ( ?, ?, ?)";
	        connect();
	         
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setString(2, aziende.getAnagrafica());
	        statement.setString(3, aziende.getUserId());
	        statement.setString(4, aziende.getPasswd());
	         
	        boolean rowInserted = statement.executeUpdate() > 0;
	        statement.close();
	        disconnect();
	        return rowInserted;
	    }
	    
	    public List<Aziende> listAllAziende() throws SQLException {
	        List<Aziende> listAziende = new ArrayList<>();
	        String sql = "SELECT * FROM azienda";
	        
	        connect();
	 
	        Statement statement = con.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);
	        
	        while (resultSet.next()) {
	        	int idAzienda = resultSet.getInt("idAzienda");
	            String Anagrafica = resultSet.getString("anagrafica");
	            String userId = resultSet.getString("userId");
	            String passwd = resultSet.getString("passwd");
	             
	            Aziende aziende = new Aziende(idAzienda, Anagrafica, userId, passwd);
	            listAziende.add(aziende);
	            
	        }
	        resultSet.close();
	        statement.close();
	        disconnect();
	         
	        return listAziende;
	    }  
	    
	    public boolean deleteAziende(Aziende aziende) throws SQLException {
	        String sql = "DELETE FROM book where book_id = ?";
	         
	        connect();
	         
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setString(1, aziende.getUserId());
	         
	        boolean rowDeleted = statement.executeUpdate() > 0;
	        statement.close();
	        disconnect();
	        return rowDeleted;     
	    }
	    
	    public Aziende getAziende(int id) throws SQLException {
	        Aziende aziende = null;
	        String sql = "SELECT * FROM aziende WHERE userId = ?";
	         
	        connect();
	         
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setInt(1, id);
	         
	        ResultSet resultSet = statement.executeQuery();
	         
	        if (resultSet.next()) {
	        	int idAzienda = resultSet.getInt("idAzienda");
	            String Anagrafica = resultSet.getString("anagrafica");
	            String userId = resultSet.getString("userId");
	            String passwd = resultSet.getString("passwd");
	             
	            aziende = new Aziende(idAzienda,Anagrafica, userId, passwd);
	        }
	         
	        resultSet.close();
	        statement.close();
	         
	        return aziende;
	    
	    }
}