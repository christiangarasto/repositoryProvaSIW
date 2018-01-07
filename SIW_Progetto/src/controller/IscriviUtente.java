package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.UtenteDao;

public class IscriviUtente extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("iscriviutente.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		String nome = req.getParameter("nome");
		String cognome = req.getParameter("cognome");
		String piva = req.getParameter("piva");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		System.out.println(nome + " " + cognome + " " + piva + " " + email + " " + password);
	
		Utente u = new Utente(piva, nome+" "+cognome);
		
			System.out.println(u.getpIva() + ", " + u.getNome());
		UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
			utenteDao.save(u);
			utenteDao.setPassword(u, password);
			utenteDao.setEmail(u, email);
			
			req.setAttribute("utente", u);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("iscriviutente.jsp");
			dispatcher.forward(req, resp);
	
	
	}
	
}
