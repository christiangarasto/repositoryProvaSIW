package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Evento;
import model.Luogo;
import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.UtenteDao;

public class DammiLuoghi extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("***Get Dammi Luoghi ***");

		HttpSession session = req.getSession();

		if (session.getAttribute("loggato") == null) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("homepage.jsp");
			dispatcher.forward(req, resp);
		} else {
			String piva = (String) session.getAttribute("piva");

			UtenteDao utentedao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
			Utente utente = utentedao.findByPrimaryKey(piva);

			List<Luogo> luoghi = null;
			if (utente != null) {
				luoghi = utentedao.findAllLocation(utente.getpIva());
				if (luoghi.size() > 0) {

					System.out.println("::::::::::::::::::::");
					for (Luogo l : luoghi) {
						System.out.println("-" + l.getNome());
					}
					System.out.println("::::::::::::::::::::");

					String jsonToReturn = new Gson().toJson(luoghi);
					resp.getWriter().write(jsonToReturn);
				}

			}
		}
	}

}
