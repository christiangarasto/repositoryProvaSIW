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

public class BachecaVisitatori extends HttpServlet 
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("* BACHECAVISITATORI doGet() *");
		
		EventoDao eventodao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
		LinkedList<Evento> eventi = eventodao.findAll();
		
		String txt = "";
		
		if(eventi != null)
		{
			int cont = 1;
			txt += "<div class=\"row\">";
			for (Evento evento : eventi) 
			{
			    txt += "<div class=\"col-sm-4\">";
				txt += "<div class=\"panel\">";
				txt += 		"<div id=\"eventoinbacheca\" class=\"panel-heading\">" +
							"<big><strong>" + evento.getTitolo() + "</strong></big></div>";
				txt += 			"<div class=\"panel-body\">"  + evento.getDescrizione() + "</div>";
				txt += 		"<div class=\"panel-footer\"> " +
							"<strong>Data :</strong> "+ evento.getData() + " <br>" +
							"<strong>Ora</strong> : " + evento.getOra() + " <br>" +
							 							evento.getLuogo().getComune() +
							 							" (" + evento.getLuogo().getProvincia() + ")" +
							"</div>";
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
		}

		resp.getWriter().write(txt);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("* BACHECAVISITATORI doPost() *");
	}
}
