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
		
		Utente u = new Utente(piva, nome+" "+cognome);		
		UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		
		Utente find = utenteDao.findByPrimaryKey(piva);
		
		if(find == null) {
			utenteDao.save(u);
			utenteDao.setPassword(piva, password);
			utenteDao.setEmail(piva, email);
			
			req.setAttribute("utente", u);
			resp.sendRedirect("homepage.jsp");
			
		}else {
			resp.sendRedirect("iscriviutente.jsp");
		}
			
	
	
	}
	
}
