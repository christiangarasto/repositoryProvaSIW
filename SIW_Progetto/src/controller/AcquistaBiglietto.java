package controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import model.Ticket;
import persistence.DatabaseManager;
import persistence.dao.TicketDao;

public class AcquistaBiglietto extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String intestatario = (String) req.getParameter("intestatario");
		String numeroCarta = (String) req.getParameter("cc");
		String cvv = (String) req.getParameter("cvv");
		String scadenza = (String) req.getParameter("scadenza");
		String codEvento = (String) req.getParameter("globale");
	
			System.out.println("Biglietto per evento " + codEvento + " acquistato da " + intestatario);

		TicketDao td = DatabaseManager.getInstance().getDaoFactory().getTicketDAO();
		
		LinkedList<Ticket> tickets = td.findAll();
		
		boolean venduto = false;
		
		for(Ticket t : tickets) {
			if(t.getEvento().getCodice().equals(codEvento) && t.getIntestatario().length() == 0) {
				t.setIntestatario(intestatario);
				td.update(t);
				venduto = true;
				break;
			}
		}
		
		String txt;
		String bottone;
		
		if(venduto) {
			txt = "<h1>Acquisto effettuato con successo!</h1>";
			bottone = "<button type=\"button\" class=\"btn btn-secondary\" onclick=\"window.location.reload()\" data-dismiss=\"modal\">Esci</button>";		
		}else {
			txt = "<h1>Biglietti esauriti!</h1>";
			bottone = "<button type=\"button\" class=\"btn btn-secondary\" onclick=\"window.location.reload()\" data-dismiss=\"modal\">Esci</button>";
		}
		
		try {
			JSONObject result = new JSONObject();
			result.put("txt", txt);
			result.put("bottone", bottone);
			resp.getWriter().write(result.toString());
		} catch (JSONException e) {e.printStackTrace();}	
		
		
	}
	
}
