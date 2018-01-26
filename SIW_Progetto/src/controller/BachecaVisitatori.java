package controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Evento;
import model.Ticket;
import persistence.DatabaseManager;
import persistence.dao.EventoDao;
import persistence.dao.TicketDao;

public class BachecaVisitatori extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("* BACHECAVISITATORI doGet() *");

		EventoDao eventodao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
		LinkedList<Evento> eventi = eventodao.findAll();

		TicketDao ticketDao = DatabaseManager.getInstance().getDaoFactory().getTicketDAO();
		LinkedList<Ticket> ticket = ticketDao.findAll();
		
		LinkedList<Evento> eventiAPagamento = new LinkedList<Evento>();
		LinkedList<Evento> eventiEsauriti = new LinkedList<Evento>();
		
		for(Evento e : eventi) {
			int bigliettiPerEvento = 0;
			int bigliettiVenduti = 0;
			for(Ticket t : ticket) {
				if(t.getEvento().getCodice().equals(e.getCodice())) {
					bigliettiPerEvento++;
					
					if(t.getIntestatario().length() > 0) {
						bigliettiVenduti++;
					}
				}
			}
			
			if(bigliettiPerEvento > 0 && bigliettiVenduti == bigliettiPerEvento) {
				eventiEsauriti.add(e);
			}else if(bigliettiPerEvento > 0){
				eventiAPagamento.add(e);
			}
			
		}
		
		String txt = "";

		if (eventi != null) {
			int cont = 1;
			for (Evento evento : eventi) {
				if(cont == 1)
					txt += "<div class=\"row\">";
				
				txt += "<div class=\"col-sm-4\">";
				txt += 		"<div class=\"panel\">";
					txt += 		"<div id=\"eventoinbacheca\" class=\"panel-heading\">" + "<big><strong>" + evento.getTitolo()
								+ "</strong></big></div>";
				txt +=	 	"<div class=\"panel-body\">" + evento.getDescrizione() + "</div>";
				txt += 		"<div class=\"panel-footer\"> " + "<strong>Luogo :</strong> " + evento.getLuogo().getNome() + " <br>";
				txt += 											"<strong>Data :</strong> " + evento.getData() + " <br>"
																+ "<strong>Ora</strong> : " + evento.getOra() + " <br>" + evento.getLuogo().getComune() + " (" +
																	evento.getLuogo().getProvincia() + ") <br>";
				if(eventiAPagamento.contains(evento)) {
					
					double prezzoBiglietto = 0;
					
					for(Ticket t : ticket) {
						if(t.getEvento().getCodice().equals(evento.getCodice()))
							prezzoBiglietto = Double.parseDouble(t.getPrezzo());
					}
					
					txt += "<button class=\"btn btn-success\" id=\"bottonePagamento\" value=\"" + evento.getCodice() + "\" onclick=\"venditaTicket()\" data-toggle=\"modal\" data-target=\"#acquisto\">Acquista biglietto per " + prezzoBiglietto + " euro</button>";
				}else if(eventiEsauriti.contains(evento)) {
					txt += "<button class=\"btn btn-danger\" disabled>Biglietti esauriti!</button>";
				}
				
				txt += 		"</div>";
				txt += "</div>";
				txt += "</div>";
				if (cont == 3) {
					txt += "</div><br>";
					cont = 0;
				}
				cont++;
			}
		}

		resp.getWriter().write(txt);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("* BACHECAVISITATORI doPost() *");
	}
}
