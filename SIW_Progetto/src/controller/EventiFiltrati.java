package controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Evento;
import persistence.DatabaseManager;
import persistence.dao.EventoDao;

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

			String eventiF = new Gson().toJson(eventiFiltrati);
			resp.getWriter().write(eventiF);

		} else {
			System.out.println("Nessun evento trovato");
		}

	}

}
