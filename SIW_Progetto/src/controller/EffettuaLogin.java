package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.UtenteDao;

public class EffettuaLogin extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("email");
		String password = req.getParameter("password");
		
		UtenteDao ut = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		
		String nomeUtente = ut.findByCredenziali(nome, password);
		
		if(nomeUtente != null) {
			HttpSession session = req.getSession();
			session.setAttribute("username", nomeUtente);
			session.setAttribute("loggato", true);		
			resp.sendRedirect("Home");
		}else {
			//per ora chiedo di iscriversi
			resp.sendRedirect("iscriviutente.jsp");
		}
	}
	
}
