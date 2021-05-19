package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ArtistDao;
import model.Artist;

@SuppressWarnings("serial")
@WebServlet("/")
public class ArtistServletList extends HttpServlet {

	private ArtistDao dao = new ArtistDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Artist> artists = dao.getAllArtists();

		req.setAttribute("artists", artists);
		req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Artist newArtist = new Artist(req.getParameter("title"));

		if (dao.addArtist(newArtist) == true) {
			dao.addArtist(newArtist);
		}
		resp.sendRedirect("/");
	}
}
