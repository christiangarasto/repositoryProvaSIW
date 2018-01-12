package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Evento;
import model.Luogo;
import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.LuogoDao;
import persistence.dao.UtenteDao;

public class GestioneEventi extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("***Get Gestione Eventi***");
		
		HttpSession session = req.getSession();
		String piva = (String) session.getAttribute("piva");

		UtenteDao utentedao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO(); 
		
		Utente utente = utentedao.findByPrimaryKey(piva);
		
		List<Luogo> luoghi = null;
		List<Evento> eventi = null;
		
		if(utente != null)
		{
			luoghi = new LinkedList<>();
			luoghi = utentedao.findAllLocation(utente.getpIva());
			if(luoghi != null)
			{
				eventi = new LinkedList<>();
				for(Luogo l : luoghi)
				{
					LuogoDao luogodao = DatabaseManager.getInstance().getDaoFactory().getLuogoDAO();
					List<Evento> tmp = new LinkedList<>();
					tmp = luogodao.findAllEvents();
					for(Evento e : tmp)
					{
						eventi.add(e);
					}
				}
				if(eventi != null)
				{
					session.setAttribute("events", true);
				}
			}
		}
		
		req.setAttribute("eventi", eventi);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***Post Gestione Eventi***");
	}

}