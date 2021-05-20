package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Album;

public class JDBCAlbumDao implements AlbumDao{
	
	public static final String JDBC_URL = System.getenv("JDBC_DATABASE_URL");

	public Connection connect() throws SQLException {
		return DriverManager.getConnection(JDBC_URL);
	}
	
	@Override
	public List<Album> getAlbums(long artistId) {

		List<Album> albums = new ArrayList<>();

		try (Connection connection = connect();
				PreparedStatement findStatement = connection.prepareStatement("SELECT AlbumId, Title, ArtistId FROM Album WHERE ArtistId = ?");
				ResultSet results = findStatement.executeQuery()) {

			findStatement.setLong(1, artistId);
			findStatement.executeQuery();
			
			while (results.next()) {
				long AlbumId = results.getLong("AlbumId");
				long ArtistId = results.getLong("ArtistId");
				String Title = results.getString("Title");
				Album newAlbum = new Album(AlbumId, ArtistId, Title);
				albums.add(newAlbum);
			}
			
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();

		}
		return albums;

	}
	
}
