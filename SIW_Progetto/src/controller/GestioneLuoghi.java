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
import persistence.dao.LuogoDao;
import persistence.dao.UtenteDao;

public class GestioneLuoghi extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("GET");
		HttpSession session = req.getSession();
		String piva = (String) session.getAttribute("piva");

		UtenteDao utentedao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();

		Utente utente = utentedao.findByPrimaryKey(piva);

		List<Luogo> luoghi = new LinkedList<>();

		if (utente != null) {
			luoghi = utentedao.findAllLocation(utente.getpIva());
		}

		session.setAttribute("luoghi", luoghi);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("2 post");
		
		String nomeLuogo = (String) req.getParameter("nomeLuogoInput");
		String provincia = (String) req.getParameter("provinciaInput");
		String comune = (String) req.getParameter("comuneInput");
		String indirizzo = (String) req.getParameter("indirizzoInput");
		
		HttpSession session = req.getSession();
		
		
		LuogoDao luogoDao = DatabaseManager.getInstance().getDaoFactory().getLuogoDAO();
		
		String pivaTitolare = (String) session.getAttribute("piva");
		
		LinkedList<Luogo> luoghi = luogoDao.findAll();
		LinkedList<Luogo> luoghiDelTitolare = new LinkedList<Luogo>();
		
		
		for(Luogo l : luoghi){
			
			if(l.getTitolare().getpIva().equals(pivaTitolare)) {
				luoghiDelTitolare.add(l);
			}
		}

		boolean luogoNuovo = true;
		for(Luogo luogo : luoghiDelTitolare) {
			
			if((luogo.getNome().toLowerCase().equals(nomeLuogo.toLowerCase())) &&
				(luogo.getProvincia().toLowerCase().equals(provincia.toLowerCase())) &&
				(luogo.getComune().toLowerCase().equals(comune.toLowerCase())) &&
				(luogo.getIndirizzo().toLowerCase().equals(indirizzo.toLowerCase()))) {
							luogoNuovo = false;
			}
		}
		
		if(luogoNuovo) {
			Luogo nuovo = new Luogo((Utente)session.getAttribute("utente"), nomeLuogo, provincia, comune, indirizzo);
			luogoDao.save(nuovo);
			req.setAttribute("luogoCreato", true);
			
		}else {
			req.setAttribute("luogoEsistente", true);
		}
		
		resp.sendRedirect("gestioneLuoghi.jsp");}
}
