package database;

import java.util.List;
import model.Album;

public interface AlbumDao {

	public List<Album> getAlbums(long artistId);
}
