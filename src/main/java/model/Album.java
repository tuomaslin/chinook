package model;

public class Album {

	private long albumId;
	private long artistId;
	private String title;

	public Album(long albumId, long artistId, String title) {
		this.albumId = albumId;
		this.artistId = artistId;
		this.title = title;
	}

	public Album(long albumId, String title) {
		this.albumId = albumId;
		this.title = title;
	}

	public Album(long albumId) {
		this.albumId = albumId;
	}

	public long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}

	public long getArtistId() {
		return artistId;
	}

	public void setArtistId(long artistId) {
		this.artistId = artistId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Album [albumId=" + albumId + ", artistId=" + artistId + ", Title=" + title + "]";
	}

}
