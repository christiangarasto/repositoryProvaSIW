package controller;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Evento;
import model.Luogo;
import model.Ticket;
import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.EventoDao;
import persistence.dao.LuogoDao;
import persistence.dao.TicketDao;
import persistence.dao.UtenteDao;

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
					LinkedList<Evento> tmp = l.getEventi();
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

		String _data = (String) req.getParameter("data");
		SimpleDateFormat formatter = new SimpleDateFormat("yyy-mm-dd");
		java.util.Date data = null;
		try {
			data = formatter.parse(_data);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		java.sql.Date sqlData = new java.sql.Date(data.getTime());

		String _ora = (String) req.getParameter("orario");
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		long ms = 0;
		try {
			ms = sdf.parse(_ora).getTime();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		java.sql.Time ora = new Time(ms);
		
		String biglietto = (String) req.getParameter("ticket");
		String numero = (String) req.getParameter("numero");
		String prezzo = "0";	//se il prezzo � 0 vuol dire che l'entrata � gratuita
		if(biglietto == "pagamento")
		{
			prezzo = req.getParameter("prezzo");
		}

		boolean evento_esistente = false;

		LuogoDao luogodao = DatabaseManager.getInstance().getDaoFactory().getLuogoDAO();
		Luogo luogo = luogodao.findByPrimaryKey(codice_luogo);

		System.out.print("il luogo con nome " + luogo.getNome() + " ha i seguenti eventi:");

		LinkedList<Evento> eventi = luogo.getEventi();
		if (eventi != null) 
		{
			for (Evento e : eventi) 
			{
				if( (e.getOra()).equals(ora) && (e.getData()).equals(sqlData) )
				{
					System.out.println("attenzione! C'� gi� un evento in programma!");
					if( (e.getTitolo()).equals(titolo) && (e.getDescrizione()).equals(descrizione) && (e.getGenere()).equals(genere) )
					{
						System.out.println("Evento gi� registrato!");
						evento_esistente = true;
						req.setAttribute("evento_esistente", true);
					}
				}
			}
			
		}

		if (!evento_esistente) 
		{
			System.out.println("Salvataggio dell'evento nel DB");
			Evento nuovoEvento = new Evento(titolo, descrizione, genere, sqlData, ora, luogo);
			EventoDao eventoDao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
			eventoDao.save(nuovoEvento);
			if(biglietto == "pagamento")
			{
				int size = Integer.parseInt(numero);
				System.out.println( size + " biglietti a pagamento ");
				for(int i = 0; i < size; i++)
				{
					Ticket ticket = new Ticket(prezzo, "", nuovoEvento);
					TicketDao ticketdao = DatabaseManager.getInstance().getDaoFactory().getTicketDAO();
					ticketdao.save(ticket);
				}
			}
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("homepage.jsp");
		dispatcher.forward(req, resp);
	}
}
