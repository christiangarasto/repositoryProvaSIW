package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.UtenteDao;

public class RimuoviProfilo extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		System.out.println("DOGET rimuoviprofilo");
		
		HttpSession session = req.getSession();
		
		String piva = (String) session.getAttribute("piva");
		
		System.out.println("PIVA: " + piva);
		if(piva != null) {
			
			UtenteDao ud = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
			Utente utente = ud.findByPrimaryKey(piva);
			if(utente != null) {
				ud.delete(utente);
				session.invalidate();
				
				RequestDispatcher dispatcher = req.getRequestDispatcher("homepage.jsp");
				dispatcher.forward(req, resp);
			}
			
		}
		
		
	}

}
