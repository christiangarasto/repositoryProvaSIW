package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Luogo;
import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.LuogoDao;
import persistence.dao.UtenteDao;

public class GestioneLuoghi extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("GET LUOGHI");

		HttpSession session = req.getSession();
		String piva = (String) session.getAttribute("piva");

		UtenteDao utentedao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		Utente utente = utentedao.findByPrimaryKey(piva);

		List<Luogo> luoghi = null;
		if (utente != null) {
			luoghi = utentedao.findAllLocation(utente.getpIva());
			if (luoghi != null) {

				// System.out.println("::::::::::::::::::::");
				// for(Luogo l : luoghi)
				// {
				// System.out.println("-" + l.getNome());
				// }
				// System.out.println("::::::::::::::::::::");
				// session.setAttribute("luoghi", luoghi);
				// session.setAttribute("locations", true);
				// System.out.println("(boolean)locations = " +
				// session.getAttribute("locations"));

				String jsonToReturn = new Gson().toJson(luoghi);
				// System.out.println(jsonToReturn);
				resp.getWriter().write(jsonToReturn);
				
				session.setAttribute("luoghi", luoghi);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nomeLuogo = req.getParameter("nomeLuogoInput");
		String provincia = (String) req.getParameter("provinciaInput");
		String comune = (String) req.getParameter("comuneInput");
		String indirizzo = (String) req.getParameter("indirizzoInput");

		HttpSession session = req.getSession();

		LuogoDao luogoDao = DatabaseManager.getInstance().getDaoFactory().getLuogoDAO();
		UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();

		String pivaTitolare = (String) session.getAttribute("piva");

		LinkedList<Luogo> luoghi = utenteDao.findAllLocation(pivaTitolare);

		Luogo luogoN = new Luogo(utenteDao.findByPrimaryKey(pivaTitolare), nomeLuogo, provincia, comune, indirizzo);
		
		System.out.println(nomeLuogo + ", " + provincia + ", " + comune + ", " + indirizzo);
		
		if (!luoghi.contains(luogoN)) {
			System.out.println("Luogo nuovo quindi aggiungo");
			luogoDao.save(luogoN);

			req.setAttribute("luogoCreato", true);

			String nuovoLuogo = new Gson().toJson(luogoN);
			resp.getWriter().write(nuovoLuogo);

		}else {

			System.out.println("Luogo non nuovo quindi niente");
		}
		
		resp.sendRedirect("gestioneLuoghi.jsp");
		
		
	}
}
