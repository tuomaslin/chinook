package database;

import java.util.List;
import model.Artist;

public interface ArtistDao {

	public List<Artist> getAllArtists();
	
	public boolean addArtist(Artist newArtist);
	
	public Artist getArtist(long artistId);
	
}
