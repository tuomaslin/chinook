package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.AlbumDao;
import database.ArtistDao;
import model.Album;
import model.Artist;

@SuppressWarnings("serial")
@WebServlet("/albums")
public class ShowAlbumsServlet extends HttpServlet {

	private AlbumDao albumDao = new AlbumDao();
	private ArtistDao artistDao = new ArtistDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String artistId = req.getParameter("ArtistId");
		long id = Long.parseLong(artistId);

		Artist artist = artistDao.getArtist(id);
		String artistName = artist.toString();

		List<Album> albums = albumDao.getAlbums(id);

		req.setAttribute("artistName", artistName);
		req.setAttribute("albums", albums);
		req.getRequestDispatcher("/WEB-INF/search.jsp").forward(req, resp);
	}

}
