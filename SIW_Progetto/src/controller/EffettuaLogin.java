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

public class EffettuaLogin extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		UtenteDao ut = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();

		String piva = ut.findByCredenziali(email, password);
		HttpSession session = req.getSession();

		if (piva != null) {

			Utente utente = ut.findByPrimaryKey(piva);

			session.setAttribute("utente", utente);
			session.setAttribute("nome", utente.getNome());
			session.setAttribute("piva", utente.getpIva());
			session.setAttribute("email", email);
			session.setAttribute("password", password);

			session.setAttribute("loggato", true);
			session.setAttribute("credenzialiErrate", false);
		} else {
			session.setAttribute("credenzialiErrate", true);
		}

		resp.sendRedirect("Home");
	}

}
