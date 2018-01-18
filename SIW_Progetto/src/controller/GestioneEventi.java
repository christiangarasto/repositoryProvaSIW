package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Evento;
import model.Luogo;
import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.LuogoDao;
import persistence.dao.UtenteDao;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
			luoghi = utentedao.findAllLocation(utente.getpIva());
			if(luoghi != null)
			{
				eventi = new LinkedList<>();
				for(Luogo l : luoghi)
				{
					List<Evento> tmp = l.getEventi();
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

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		System.out.println("***Post Gestione Eventi***");
		
		String titolo = (String) req.getParameter("titolo");
		String codice_luogo = (String) req.getParameter("location");
		String genere = (String) req.getParameter("genere");
		String descrizione = (String) req.getParameter("descrizione");
		
		String data = (String) req.getParameter("data");
		data = data.replace("T", " ");
		data += ":00";
		System.out.println(data);

		java.sql.Date sqlDate = null;
	    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		    try {
		        java.util.Date utilDate = format.parse(data);
		        sqlDate = new java.sql.Date(utilDate.getTime());
		        System.out.println("sqlDate: " + sqlDate);
		    } catch (ParseException e) {
		        e.printStackTrace();
		    }

		boolean evento_esistente = false;

		LuogoDao luogodao = DatabaseManager.getInstance().getDaoFactory().getLuogoDAO();
		Luogo luogo = luogodao.findByPrimaryKey(codice_luogo);
		
		System.out.print("il luogo con nome " + luogo.getNome() + " ha i seguenti eventi:");
		
		LinkedList<Evento> eventi = luogo.getEventi();
		if(eventi != null)
		{
			for(Evento e : eventi)
			{
				System.out.println(e.toString());
				if((e.getTitolo()).equals(titolo) && (e.getDescrizione()).equals(descrizione))// && e.getData().equals(data))?????????
				{
					evento_esistente = true;
					resp.sendRedirect("homepage.jsp");
				}
			}
		}
		else 
		{System.out.println("NESSUNO");}
		
		System.out.println("::::::::::::::::::::::::::");
		
		if(evento_esistente){
			System.out.println("L'evento esiste già nel DB");
			req.setAttribute("evento_esistente", true);
		}
		else {
			System.out.println("Salvataggio dell'evento nel DB");
//			Evento nuovo = new Evento(descrizione, sqlDate, luogo);
//			EventoDao eventoDao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
//			eventoDao.save(nuovo);
		}
		System.out.println("FINE");
		resp.sendRedirect("homepage.jsp");
	}
}
