package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Luogo;
import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.UtenteDao;

public class GestioneEvento extends HttpServlet 
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("GET");
		HttpSession session = req.getSession();
		String piva = (String) session.getAttribute("piva");

		UtenteDao utentedao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO(); 
		
		Utente utente = utentedao.findByPrimaryKey(piva);
		
		List<Luogo> luoghi = new LinkedList<>();
		
		if(utente != null)
		{
			luoghi = utentedao.findAllLocation(utente.getpIva());
		}
		
		session.setAttribute("luoghi", luoghi);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("POST");
	}
}
