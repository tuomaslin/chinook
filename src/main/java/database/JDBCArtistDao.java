package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Artist;

public class JDBCArtistDao implements ArtistDao {

	public static final String JDBC_URL = System.getenv("JDBC_DATABASE_URL");

	public Connection connect() throws SQLException {
		return DriverManager.getConnection(JDBC_URL);
	}
	
	@Override
	public List<Artist> getAllArtists() {

		List<Artist> artistList = new ArrayList<>();

		try (Connection connection = connect();
				PreparedStatement statement = connection
						.prepareStatement("SELECT ArtistId, Name FROM Artist ORDER BY Name ASC");
				ResultSet results = statement.executeQuery()) {

			while (results.next()) {
				long artistId = results.getLong("ArtistId");
				String name = results.getString("Name");
				Artist newArtist = new Artist(artistId, name);
				artistList.add(newArtist);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return artistList;
	}
	
	@Override
	public boolean addArtist(Artist newArtist) {

		String i = newArtist.getName();
		boolean b = false;

		try (Connection connection = connect();
				PreparedStatement addStatement = connection.prepareStatement("INSERT INTO Artist (Name) VALUES (?)");
				PreparedStatement checkStatement = connection.prepareStatement("SELECT * FROM Artist WHERE Name = ?");
				ResultSet r = checkStatement.executeQuery()) {

			checkStatement.setString(1, i);
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

	@Override
	public Artist getArtist(long artistId) {

		Artist searchResult = new Artist(artistId, null);

		try (Connection connection = connect();
				PreparedStatement findStatement = connection
						.prepareStatement("SELECT * FROM Artist WHERE ArtistId = ?");
				ResultSet results = findStatement.executeQuery()) {

			findStatement.setLong(1, artistId);
			findStatement.executeQuery();

			while (results.next()) {
				String name = results.getString("Name");
				searchResult = new Artist(name);
			}

		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();

		}
		return searchResult;

	}
}
