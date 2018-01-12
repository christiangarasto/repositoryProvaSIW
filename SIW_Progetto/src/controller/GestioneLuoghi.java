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

public class GestioneLuoghi extends HttpServlet 
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
		
		String nomeLuogo = (String) req.getParameter("nomeLuogoInput");
		String provincia = (String) req.getParameter("provinciaInput");
		String comune = (String) req.getParameter("comuneInput");
		String indirizzo = (String) req.getParameter("indirizzoInput");
		
		System.out.println(nomeLuogo + ", " + provincia + ", " + comune+ ", " + indirizzo);
		
		HttpSession session = req.getSession();
		
		
		LuogoDao luogoDao = DatabaseManager.getInstance().getDaoFactory().getLuogoDAO();
		
		String pivaTitolare = (String) session.getAttribute("piva");
		
		LinkedList<Luogo> luoghiDelTitolare = luogoDao.findByTitolare(pivaTitolare);
		
		boolean luogoNuovo = false;
		
		for(Luogo luogo : luoghiDelTitolare) {
			
			if(luogo.getNome().toLowerCase() != nomeLuogo.toLowerCase() ||
			   luogo.getProvincia().toLowerCase() != provincia.toLowerCase() ||
			   luogo.getComune().toLowerCase() != comune.toLowerCase() ||
			   luogo.getIndirizzo().toLowerCase() != indirizzo.toLowerCase()) {
							luogoNuovo = true;
			}
		}
		
		if(luogoNuovo) {
			
			Luogo nuovo = new Luogo((Utente)session.getAttribute("utente"), nomeLuogo, provincia, comune, indirizzo);
			luogoDao.save(nuovo);
			session.setAttribute("luogoCreato", true);
			
		}else {
			session.setAttribute("luogoEsistente", true);
		}
		
		

		resp.sendRedirect("gestioneLuoghi.jsp");}}
