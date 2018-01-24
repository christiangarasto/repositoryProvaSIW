package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Evento;
import persistence.DatabaseManager;
import persistence.dao.EventoDao;

public class EliminaEventi extends HttpServlet 
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("Elimina Eventi doPost()");
		String eventToDelete = req.getParameter("deletEvent");
		EventoDao eventodao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
		Evento evento = eventodao.findByPrimaryKey(eventToDelete);
		eventodao.delete(evento);
		
	}
}
