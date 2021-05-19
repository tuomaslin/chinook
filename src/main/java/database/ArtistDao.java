package databasae;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Artist;

public class ArtistDao {
	
	public static final String JDBC_URL = System.getenv("JDBC_DATABASE_URL");

	public Connection connect() throws SQLException {
		return DriverManager.getConnection(JDBC_URL);
	}

	public List<Artist> getAllArtists() {
		
		List<Artist> selectAll = new ArrayList<>();
		
		try (Connection connection = connect();
	    	    PreparedStatement statement = connection.prepareStatement("SELECT ArtistId, Name FROM Artist ORDER BY Name ASC");
	    	    ResultSet results = statement.executeQuery()) {
	    	
	    	while (results.next()) {
	    		long artistId = results.getLong("ArtistId");
	    		String name = results.getString("Name");
	    		Artist newArtist = new Artist(artistId, name);
	    		selectAll.add(newArtist);
	    	}
	    	 
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    return selectAll;
	}
	
	public Artist getId(long id) {
    
		Artist searchResult = new Artist(id, null);

		try (Connection connection = connect();
				PreparedStatement findStatement = connection.prepareStatement("SELECT AlbumId, Title FROM Album WHERE ArtistId = ?");
				ResultSet results = findStatement.executeQuery()) {

			findStatement.setLong(1, id);
			findStatement.executeQuery();

			if (results.next()) {
				long resultId = results.getLong("AlbumId");
				String title = results.getString("Title");
				searchResult = new Artist(resultId, title);
			} else {
				return null;
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();

		}
		return searchResult;

	}
	
	public boolean addArtist(Artist newItem) {
		   
	    String i = newItem.getName();
	    boolean b = false;
	   	 
	   	 try (Connection connection = connect();
	   		 PreparedStatement addStatement = connection.prepareStatement("INSERT INTO Artist (Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
	   		 PreparedStatement checkStatement= connection.prepareStatement ("SELECT * FROM Artist WHERE Name = ?");
	   		 ResultSet r = checkStatement.executeQuery();
	   		 ResultSet rs = addStatement.getGeneratedKeys()) {
	   		 
	   		checkStatement.setString (1, i);
			checkStatement.executeQuery();
	   		 
	   		 if (r.next()) {
	   			 b = false;
	   		 } else {
	   			 addStatement.setString(1, i);
	   			 addStatement.executeUpdate();
	   			 b = true;
	   		 }

	   		
	   	 } catch (SQLException e) {
	   		 e.printStackTrace();
	   	 }
	   	 return b;
	}
}
