package controller;

import java.io.IOException;
import java.util.LinkedList;

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

public class ModificaEventi extends HttpServlet 
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("***Modifica Eventi doGet()***");
		
		String eventToModify = req.getParameter("cod_evento");
		System.out.println(eventToModify);

		EventoDao eventodao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
		Evento evento = eventodao.findByPrimaryKey(eventToModify);
		
		String txt = "";
		
		txt	+=		"<div class=\"form-group\" align=\"center\">" +
						"<label for=\"titolo\">Titolo: </label>" +
						"<input type=\"text\" name=\"titolo\" value=\""+evento.getTitolo()+"\"><br></div>" +
					"</div>" +
					"<div class=\"form-group\"  align=\"center\">" + 
						"<select id=\"genere\" name=\"genere\" class=\"btn btn-default\">" + 
							"<option value=\"\">Genere</option>" + 
							"<option value=\"arte\">Arte</option>" + 
							"<option value=\"cultura\">Cultura</option>" + 
							"<option value=\"gastronomia\">Gastronomia</option>" + 
							"<option value=\"musica\">Musica</option>" + 
							"<option value=\"sport\">Sport</option>" + 
						"</select>" + 
					"</div>" +
					"<div class=\"form-group\" id=\"locationmodify\"  align=\"center\">" +
					"</div>" +
					"<div class=\"form-group\" align=\"center\">" +
						"<textarea rows=\"3\" type=\"text\" name=\"descrizione\">"+evento.getDescrizione()+"</textarea>" +
					"</div>" +
					"<div class=\"form-inline\">" +
					"<div class=\"form-group\"  align=\"center\">" +
						"<label for=\"data\">Data svolgimento:</label> <input name=\"data\" type=\"date\" value=\""+evento.getData()+"\"/>" +
					"</div>" +
					"<div class=\"form-group\"  align=\"center\">" +
						"<label for=\"orario\">Ora:</label> <input name=\"orario\" type=\"time\" value=\""+evento.getOra()+"\"/>" +
					"</div>" +
					"</div>" +
					"<div class=\"form-check form-inline\"  align=\"center\">" + 
						"<input type=\"checkbox\" class=\"form-check-input\" id=\"pagamento\">" + 
						"<label class=\"form-check-label\" for=\"pagamento\">Pagamento</label>" +
						"<input type=\"checkbox\" class=\"form-check-input\" id=\"free\">" + 
						"<label class=\"form-check-label\" for=\"free\">Gratis</label>" +
					"</div>"  +
					"<div class=\"form-group\"  align=\"center\">" +
						"<label for=\"ticket\">Numero Ticket: </label>" +
						"<input type=\"text\" name=\"numero\"><br>" +
					"</div>" +
					"<div class=\"form-group\"  align=\"center\">" +
						"<label for=\"prezzo\">Prezzo Singolo Ticket: </label>" +
						"<input type=\"text\" name=\"prezzo\"><br>" +
					"</div>";
		
		resp.getWriter().write(txt);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("***Modifica Eventi doPost()***");
		
		String titolo = (String) req.getParameter("titolo");
		String descrizione = (String) req.getParameter("descrizione");
		String genere = (String) req.getParameter("genere");
		String luogo = (String) req.getParameter("luogo");
		
		System.out.println(titolo + "," + descrizione+ "," + genere+ "," + luogo);

	}
}