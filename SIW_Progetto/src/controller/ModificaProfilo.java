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
		
		System.out.println(nomeNuovo + ", " + pivaNuovo + ", " + emailNuovo + ", " + passwordNuovo + ", " + confermaPasswordNuovo);
		
		
		boolean aggiornamentoDaEseguire = true;

//		if(pivaNuovo.length() != 11 ||
//				   passwordNuovo != confermaPasswordNuovo ||
//				   !emailValida(emailNuovo)) {
//					System.out.println("nuovo piva, dimensione: " + pivaNuovo.length());
//					System.out.println(passwordNuovo + " - " + confermaPasswordNuovo);
//					aggiornamentoDaEseguire = false;
//				}
//				
		
		if(pivaNuovo.length() != 11) {
			System.out.println("Dimensione piva non corretta: " + pivaNuovo.length());
			aggiornamentoDaEseguire = false;
			
		}
		if(!passwordNuovo.equals(confermaPasswordNuovo)) {
			System.out.println("Password diverse: " + passwordNuovo + " - " + confermaPasswordNuovo);
			aggiornamentoDaEseguire = false;
			
		}
		if(!emailValida(emailNuovo)) {
			System.out.println("email non valida");
			aggiornamentoDaEseguire = false;

		}
		
		
		UtenteDao ud = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		Utente utenteDaModificare = ud.findByPrimaryKey(pivaNuovo);
		
		HttpSession session = req.getSession();

		boolean profiloModificato = false;
		
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
				
				profiloModificato = true;

				session.setAttribute("profiloModificato", true);
			}
		}
		
		session.setAttribute("profiloModificato", profiloModificato);
		
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
			}else if(nAT == 1 && nDOT == 0) {
				dopoAT += email.charAt(i);
			}else if(nDOT == 1) {
				dopoDOT += email.charAt(i);
			}		
		}
		
//		System.out.println("Controllo email:");
//			System.out.println("prima@: " + primaAT + "\ndopo@: " + dopoAT + "\ndopoDOT: " + dopoDOT + "\nnAT: " + nAT + "\nnDOT: " + nDOT);
		
		
		
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
