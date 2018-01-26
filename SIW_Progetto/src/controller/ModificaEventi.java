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

import model.Evento;
import model.Luogo;
import model.Ticket;
import persistence.DatabaseManager;
import persistence.dao.EventoDao;
import persistence.dao.LuogoDao;
import persistence.dao.TicketDao;

public class ModificaEventi extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("***Modifica Eventi doGet()***");

		String eventToModify = req.getParameter("cod_evento");
		System.out.println(eventToModify);

		EventoDao eventodao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
		Evento evento = eventodao.findByPrimaryKey(eventToModify);

		String txt = "";

		txt += "<div class=\"form-group\" align=\"center\">" + "<input type=\"hidden\" name=\"codice_evento\" value=\""
				+ evento.getCodice() + "\">" + "<label for=\"titolo\">Titolo: </label>"
				+ "<input type=\"text\" name=\"titolo\" value=\"" + evento.getTitolo() + "\"><br></div>" + "</div>"
				+ "<div class=\"form-group\"  align=\"center\">"
				+ "<select id=\"genere\" name=\"genere\" class=\"btn btn-default\">"
				+ "<option value=\"\">Genere</option>" + "<option value=\"arte\">Arte</option>"
				+ "<option value=\"cultura\">Cultura</option>" + "<option value=\"gastronomia\">Gastronomia</option>"
				+ "<option value=\"musica\">Musica</option>" + "<option value=\"sport\">Sport</option>" + "</select>"
				+ "</div>" + "<div class=\"form-group\" id=\"locationmodify\"  align=\"center\">" + "</div>"
				+ "<div class=\"form-group\" align=\"center\">"
				+ "<textarea rows=\"3\" type=\"text\" name=\"descrizione\">" + evento.getDescrizione() + "</textarea>"
				+ "</div>" + "<div class=\"form-inline\">" + "<div class=\"form-group\"  align=\"center\">"
				+ "<label for=\"data\">Data svolgimento:</label> <input name=\"data\" type=\"date\" value=\""
				+ evento.getData() + "\"/>" + "</div>" + "<div class=\"form-group\"  align=\"center\">"
				+ "<label for=\"orario\">Ora:</label> <input name=\"orario\" type=\"time\" value=\"" + evento.getOra()
				+ "\"/>" + "</div>" + "</div>" + "<div class=\"form-check form-inline\"  align=\"center\">"
				+ "<input type=\"checkbox\" class=\"form-check-input\" id=\"pagamento\" name=\"pagamento\">"
				+ "<label class=\"form-check-label\" for=\"pagamento\">Pagamento</label>"
				+ "<input type=\"checkbox\" class=\"form-check-input\" id=\"free\" name=\"free\">"
				+ "<label class=\"form-check-label\" for=\"free\">Gratis</label>" + "</div>"
				+ "<div class=\"form-group\"  align=\"center\">" + "<label for=\"ticket\">Numero Ticket: </label>"
				+ "<input type=\"text\" name=\"numero\"><br>" + "</div>"
				+ "<div class=\"form-group\"  align=\"center\">"
				+ "<label for=\"prezzo\">Prezzo Singolo Ticket: </label>" + "<input type=\"text\" name=\"prezzo\"><br>"
				+ "</div>";

		resp.getWriter().write(txt);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("***Modifica Eventi doPost()***");
		String codice = (String) req.getParameter("codice_evento");

		EventoDao eventodao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
		Evento evento = eventodao.findByPrimaryKey(codice);

		String titolo = (String) req.getParameter("titolo");
		String descrizione = (String) req.getParameter("descrizione");
		String genere = (String) req.getParameter("genere");
		String luogo = (String) req.getParameter("location");

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

		if (luogo != "null" && titolo != "null") {
			LuogoDao luogodao = DatabaseManager.getInstance().getDaoFactory().getLuogoDAO();
			Luogo l = luogodao.findByPrimaryKey(luogo);

			evento.setTitolo(titolo);
			evento.setGenere(descrizione);
			evento.setLuogo(l);
			evento.setDescrizione(descrizione);
			evento.setData(sqlData);
			evento.setOra(ora);

			String ticket_pagamento = (String) req.getParameter("pagamento");
			String ticket_gratis = (String) req.getParameter("gratis");
			String numero = (String) req.getParameter("numero");
			String prezzo = (String) req.getParameter("prezzo");

			eventodao.update(evento);

			if (!(ticket_pagamento.equals(ticket_gratis))) {
				System.out.println("valore gratis o pagamento esatto");

				TicketDao ticketdao = DatabaseManager.getInstance().getDaoFactory().getTicketDAO();
				LinkedList<Ticket> tks = ticketdao.findAll();
				LinkedList<Ticket> tickets = new LinkedList<Ticket>();
				if (tks.size() > 0) {
					for (Ticket t : tks) {
						if (t.getEvento().getCodice().equals(evento.getCodice())) {
							tickets.add(t);
						}
					}
				}

				if (ticket_pagamento != "null" && numero != "null" && prezzo != "null") {

					int n = Integer.parseInt(numero);
					System.out.println(n + "ticket a pagamento");
					int cont = 1;

					if (n > tickets.size()) {
						System.out.println((n - tickets.size()) + " ticket da aggiungere");
						for (int i = 0; i < (n - tickets.size()); i++) {
							System.out.println("aggiungo...");
							Ticket tmp = new Ticket(prezzo, "", evento);
							ticketdao.save(tmp);
						}

					} else if (n < tickets.size()) {
						System.out.println((tickets.size() - n) + " ticket da eliminare");
						cont = 1;
						for (Ticket t : tickets) {
							if (cont <= (tickets.size() - n)) {
								ticketdao.delete(t);
							}
							cont++;
						}

					}
				} else if (ticket_gratis != "null") {
					System.out.println("Evento gratuito");
					for (Ticket t : tickets) {
						if (t.getEvento().getCodice().equals(evento.getCodice()))
							ticketdao.delete(t);
					}
				}
			} else {
				System.out.println("Errore nella compilazione del form Modifica 1");
			}
		}
		System.out.println("Errore nella compilazione del form Modifica 2 o uscita");
		RequestDispatcher dispatcher = req.getRequestDispatcher("homepage.jsp");
		dispatcher.forward(req, resp);
	}
}