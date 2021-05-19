package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databasae.AlbumDao;
import model.Album;

@WebServlet("/albums")
public class ShowAlbumsServlet extends HttpServlet{

		private AlbumDao dao = new AlbumDao();
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String artistId = req.getParameter("ArtistId");
			long id = Long.parseLong(artistId);
			
			List<Album> albums = dao.getAlbum(id);

			req.setAttribute("albums", albums);
			req.getRequestDispatcher("/WEB-INF/search.jsp").forward(req, resp);
		}
		
		
		/*@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			Album newArtist = new Album(0, req.getParameter("artist"));
			String ArtistId = req.getParameter("ArtistId=");
			long id = Long.parseLong(ArtistId);
			
			dao.getAlbum(id);
			resp.sendRedirect("/albums");
		}*/
	}
