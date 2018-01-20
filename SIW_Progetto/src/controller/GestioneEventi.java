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
import persistence.dao.LuogoDao;
import persistence.dao.UtenteDao;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GestioneEventi extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("***Get Gestione Eventi***");

		HttpSession session = req.getSession();
		String piva = (String) session.getAttribute("piva");

		UtenteDao utentedao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();

		Utente utente = utentedao.findByPrimaryKey(piva);

		LinkedList<Luogo> luoghi = null;
		LinkedList<Evento> eventi = null;

		if (utente != null) {
			luoghi = utentedao.findAllLocation(utente.getpIva());
			if (luoghi != null) {
				eventi = new LinkedList<>();
				for (Luogo l : luoghi) {
					List<Evento> tmp = l.getEventi();
					if (tmp != null) {
						for (Evento e : tmp) {
							eventi.add(e);
						}
					}
				}
				if (eventi != null) {
					session.setAttribute("events", true);
				}
			}
		}
		req.setAttribute("eventi", eventi);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("***Post Gestione Eventi***");

		String titolo = (String) req.getParameter("titolo");
		String codice_luogo = (String) req.getParameter("location");
		String genere = (String) req.getParameter("genere");
		String descrizione = (String) req.getParameter("descrizione");

		String data = (String) req.getParameter("data");
		System.out.println(data);
		String ora = (String) req.getParameter("orario");
		System.out.println(ora);
		
		String ticket = (String) req.getParameter("ticket");
		String numero = (String) req.getParameter("numero");
		System.out.println(numero);
		String prezzo = "0";	//se il prezzo è 0 vuol dire che l'entrata è gratuita
		if(ticket == "pagamento")
		{
			prezzo = req.getParameter("prezzo");
		}

		boolean evento_esistente = false;

		LuogoDao luogodao = DatabaseManager.getInstance().getDaoFactory().getLuogoDAO();
		Luogo luogo = luogodao.findByPrimaryKey(codice_luogo);

		System.out.print("il luogo con nome " + luogo.getNome() + " ha i seguenti eventi:");

		LinkedList<Evento> eventi = luogo.getEventi();
		if (eventi != null) {
			for (Evento e : eventi) {
				System.out.println(e.toString());
				if ((e.getTitolo()).equals(titolo) && (e.getDescrizione()).equals(descrizione))// &&
																								// e.getData().equals(data))?????????
				{
					evento_esistente = true;
					resp.sendRedirect("homepage.jsp");
				}
			}
		} else {
			System.out.println("NESSUNO");
		}

		System.out.println("::::::::::::::::::::::::::");

		if (evento_esistente) {
			System.out.println("L'evento esiste già nel DB");
			req.setAttribute("evento_esistente", true);
		} else {
			System.out.println("Salvataggio dell'evento nel DB");
			// Evento nuovo = new Evento(descrizione, sqlDate, luogo);
			// EventoDao eventoDao =
			// DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
			// eventoDao.save(nuovo);
		}
		System.out.println("FINE");
		RequestDispatcher dispatcher = req.getRequestDispatcher("homepage.jsp");
		dispatcher.forward(req, resp);
	}
}
