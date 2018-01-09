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

public class ModificaProfilo extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nomeNuovo = (String) req.getParameter("input_nome");
		String pivaNuovo = (String) req.getParameter("input_piva");
		String emailNuovo = (String) req.getParameter("input_email");
		String passwordNuovo = (String) req.getParameter("input_password");
		String confermaPasswordNuovo = (String) req.getParameter("input_confermapassword");
		
		boolean aggiornamentoDaEseguire = true;
		
		if(pivaNuovo.length() != 11 ||
		   passwordNuovo != confermaPasswordNuovo ||
		   !emailValida(emailNuovo)) {
			System.out.println(passwordNuovo + " - " + confermaPasswordNuovo);
			aggiornamentoDaEseguire = false;
		}
		
		
		UtenteDao ud = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		Utente utenteDaModificare = ud.findByPrimaryKey(pivaNuovo);
		
		HttpSession session = req.getSession();

		if(utenteDaModificare != null && aggiornamentoDaEseguire) {			
			if(ud.passwordCambiata(utenteDaModificare.getpIva(), passwordNuovo) || 
			   ud.emailCambiata(utenteDaModificare.getpIva(), emailNuovo) ||
			   utenteDaModificare.getNome() != nomeNuovo ||
			   utenteDaModificare.getpIva() != pivaNuovo) {
				
				ud.update(new Utente(pivaNuovo, nomeNuovo));
				ud.setEmail(pivaNuovo, emailNuovo);
				ud.setPassword(pivaNuovo, passwordNuovo);
				
				session.setAttribute("nome", nomeNuovo);
				session.setAttribute("piva", pivaNuovo);
				session.setAttribute("email", emailNuovo);
				session.setAttribute("password", passwordNuovo);
				
				
			}
		}
		
		session.setAttribute("profiloModificato", aggiornamentoDaEseguire);
		
		
		resp.sendRedirect("gestioneProfilo.jsp");
	}

	private boolean emailValida(String email) {
		
		int nAT = 0;
		int nDOT = 0;
		
		String primaAT = "";
		String dopoAT = "";
		String dopoDOT = "";
		
		for(int i = 0; i < email.length(); i++) {
			if(email.charAt(i) == '@')
				nAT++;
			if(email.charAt(i) == '.')
				nDOT++;
			
			if(nAT == 0) {
				primaAT += email.charAt(i);
			}else if(nAT == 1) {
				dopoAT += email.charAt(i);
			}else if(nDOT == 1) {
				dopoDOT += email.charAt(i);
			}		
		}
		
		if(nAT == 0 ||
		   nDOT == 0 ||
		   primaAT.length() == 0 ||
		   dopoAT.length() == 0 ||
		   dopoDOT.length() == 0) {
			System.out.println("Email non valida");
			return false;
		}
		System.out.println("Email valida");
		return true;
	}
}
