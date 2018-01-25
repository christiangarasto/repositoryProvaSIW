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
							
		String eventomod = new Gson().toJson(evento);
		resp.getWriter().write(eventomod);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("***Modifica Eventi doPost()***");
	}
}