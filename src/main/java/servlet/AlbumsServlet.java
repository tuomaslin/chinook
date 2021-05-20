package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.JDBCAlbumDao;
import database.JDBCArtistDao;
import model.Album;
import model.Artist;

@SuppressWarnings("serial")
@WebServlet("/albums")
public class AlbumsServlet extends HttpServlet {

	private JDBCAlbumDao albumDao = new JDBCAlbumDao();
	private JDBCArtistDao artistDao = new JDBCArtistDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long artistId = Long.parseLong(req.getParameter("ArtistId"));

		Artist artist = artistDao.getArtist(artistId);
		String artistName = artist.getName();

		List<Album> albums = albumDao.getAlbums(artistId);

		req.setAttribute("artistName", artistName);
		req.setAttribute("albums", albums);
		req.getRequestDispatcher("/WEB-INF/albums.jsp").forward(req, resp);
	}

}
