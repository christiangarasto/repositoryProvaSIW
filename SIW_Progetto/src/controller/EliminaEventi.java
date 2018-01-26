package controller;

import java.io.IOException;
import java.util.LinkedList;

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
import persistence.dao.EventoDao;
import persistence.dao.LuogoDao;
import persistence.dao.UtenteDao;

public class EliminaEventi extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("***Elimina Eventi doPost()***");

		String eventToDelete = req.getParameter("cod_evento");
		System.out.println(eventToDelete);

		EventoDao eventodao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
		Evento evento = eventodao.findByPrimaryKey(eventToDelete);
		eventodao.delete(evento);

		HttpSession session = req.getSession();

		String piva = (String) session.getAttribute("piva");

		UtenteDao utentedao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		Utente utente = utentedao.findByPrimaryKey(piva);

		LinkedList<Luogo> luoghi = null;
		LinkedList<Evento> eventi = null;

		if (utente != null) {
			luoghi = utentedao.findAllLocation(utente.getpIva());
			if (luoghi.size() > 0) {
				eventi = new LinkedList<>();
				for (Luogo l : luoghi) {
					LuogoDao luogodao = DatabaseManager.getInstance().getDaoFactory().getLuogoDAO();
					LinkedList<Evento> tmp = luogodao.findAllEvents(l.getCodice());
					if (tmp != null) {
						for (Evento e : tmp) {
							eventi.add(e);
						}
					}
					if (eventi != null) {
						req.setAttribute("events", true);

						String eventiUtente = new Gson().toJson(eventi);
						resp.getWriter().write(eventiUtente);
					}
				}
			} else {
				req.setAttribute("events", false);
			}
		}
	}
}
