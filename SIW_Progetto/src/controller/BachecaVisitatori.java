package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.DatabaseManager;
import persistence.dao.EventoDao;

public class BachecaVisitatori extends HttpServlet 
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("* BACHECAVISITATORI doGet() *");
		
		EventoDao eventodao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("* BACHECAVISITATORI doPost() *");
	}
}
