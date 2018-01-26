package controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Evento;
import model.Ticket;
import persistence.DatabaseManager;
import persistence.dao.EventoDao;
import persistence.dao.TicketDao;

public class EventiFiltrati extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Chiamo get eventifiltrati");

		String sceltaFiltro = req.getParameter("sceltaFiltro");
		String valoreFiltro = req.getParameter("valoreFiltro");

		EventoDao ed = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
		LinkedList<Evento> eventiFiltrati = null;

		switch (sceltaFiltro) {
		case "luogo":
			eventiFiltrati = ed.eventiPerLuogo(valoreFiltro, false); // OK
			break;
		case "genere":
			eventiFiltrati = ed.eventiPerGenere(valoreFiltro); // OK
			break;
		case "data":
			eventiFiltrati = ed.eventiPerData(valoreFiltro);
			break;
		case "oggi":
			eventiFiltrati = ed.eventiOggi(valoreFiltro);
			break;
		case "ora":
			eventiFiltrati = ed.eventiPerOra(valoreFiltro);
			break;
		case "comune":
			eventiFiltrati = ed.eventiPerComune(valoreFiltro); // OK
			break;
		case "provincia":
			eventiFiltrati = ed.eventiPerProvincia(valoreFiltro); // OK
			break;
		case "gratuiti":
			eventiFiltrati = ed.eventiGratuiti(); // OK
			break;
		case "pagamento":
			eventiFiltrati = ed.eventiAPagamento(); // OK
			break;
		}

		if (eventiFiltrati.size() > 0) {
			System.out.println("Eventi inseriti, totali inseriti: " + eventiFiltrati.size());
			
			LinkedList<Evento> eventiAPagamento = new LinkedList<Evento>();
			LinkedList<Evento> eventiEsauriti = new LinkedList<Evento>();
			
			TicketDao ticketDao = DatabaseManager.getInstance().getDaoFactory().getTicketDAO();
			LinkedList<Ticket> ticket = ticketDao.findAll();
			
			for(Evento e : eventiFiltrati) {
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
				
				if(bigliettiVenduti == bigliettiPerEvento) {
					eventiEsauriti.add(e);
				}else if(bigliettiPerEvento > 0){
					eventiAPagamento.add(e);
				}
				
			}
			
			String txt = "";
			int cont = 1;
			txt += "<div class=\"row\">";
			for (Evento e : eventiFiltrati) 
			{
			    txt += "<div class=\"col-sm-4\">";
				txt += "<div class=\"panel\">";
				txt += 		"<div id=\"eventoinbacheca\" class=\"panel-heading\">" +
							"<big><strong>" + e.getTitolo() + "</strong></big></div>";
				txt += 			"<div class=\"panel-body\">"  + e.getDescrizione() + "</div>";
				txt += 		"<div class=\"panel-footer\"> " +
							"<strong>Luogo :</strong> " + e.getLuogo().getNome() + " <br>" +
							"<strong>Data :</strong> "+ e.getData() + " <br>" +
							"<strong>Ora</strong> : " + e.getOra() + " <br>" +
							 							e.getLuogo().getComune() +
							 							" (" + e.getLuogo().getProvincia() + ") <br>";
							 								
						if(eventiAPagamento.contains(e)) {System.out.println("L'evento " + e.getCodice() + " ha ancora biglietti");
						
							double prezzoBiglietto = 0;
						
							for(Ticket t : ticket) {
								if(t.getEvento().getCodice().equals(e.getCodice()))
									prezzoBiglietto = Double.parseDouble(t.getPrezzo());
							}
						
							txt += "<button class=\"btn btn-success\" id=\"bottonePagamento\" value=\"" + e.getCodice() + "\" onclick=\"venditaTicket()\" data-toggle=\"modal\" data-target=\"#acquisto\">Acquista biglietto per " + prezzoBiglietto + " euro</button>";

						}else if(eventiEsauriti.contains(e)) {System.out.println("L'evento " + e.getCodice() + " non ha biglietti");
							txt += "<button class=\"btn btn-danger\" disabled>Biglietti esauriti!</button>";
						}
						
						txt += "</div>";
			
				txt += "</div>";
				txt += "</div>";
				if(cont == 3)
				{
					txt += "</div><br>";
					txt += "<div class=\"row\">";
					cont = 0;
				}
				cont++;
			}
			txt += "</div>";
		
			resp.getWriter().write(txt);

		} else {
			System.out.println("Nessun evento trovato");
		}

	}

}
