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
		
		String jsonToReturn = new Gson().toJson(eventi);
		resp.getWriter().write(jsonToReturn);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("* BACHECAVISITATORI doPost() *");
	}
}
