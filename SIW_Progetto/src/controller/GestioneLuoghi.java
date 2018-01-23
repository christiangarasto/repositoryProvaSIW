package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import model.Luogo;
import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.LuogoDao;
import persistence.dao.UtenteDao;

public class GestioneLuoghi extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("GET LUOGHI");

		HttpSession session = req.getSession();
		String piva = (String) session.getAttribute("piva");

		UtenteDao utentedao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		Utente utente = utentedao.findByPrimaryKey(piva);

		List<Luogo> luoghi = null;
		if (utente != null) {
			luoghi = utentedao.findAllLocation(utente.getpIva());
			if (luoghi != null) {

//				 System.out.println("::::::::::::::::::::");
//				 for(Luogo l : luoghi)
//				 {
//				 System.out.println("-" + l.getNome());
//				 }
//				 System.out.println("::::::::::::::::::::");

				String jsonToReturn = new Gson().toJson(luoghi);
				resp.getWriter().write(jsonToReturn);

				session.setAttribute("luoghi", luoghi);
			}
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("gestioneLuoghi.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("POST LUOGHI");

		HttpSession session = req.getSession();
		String piva = (String) session.getAttribute("piva");

		UtenteDao ud = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		Utente titolare = ud.findByPrimaryKey(piva);

		if (titolare != null) {

			StringBuffer jsonReceived = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));

			String line = reader.readLine();
			while (line != null) {
				jsonReceived.append(line);
				line = reader.readLine();
			}

			System.out.println("jsonReceived: " + jsonReceived.toString());
			Luogo luogo;

			try {
				JSONObject json = new JSONObject(jsonReceived.toString());

				luogo = new Luogo();
				luogo.setNome(json.getString("nomeLuogoInput"));
				luogo.setProvincia(json.getString("provinciaInput"));
				luogo.setComune(json.getString("comuneInput"));
				luogo.setIndirizzo(json.getString("indirizzoInput"));
				luogo.setTitolare(titolare);

				LuogoDao luogoDao = DatabaseManager.getInstance().getDaoFactory().getLuogoDAO();
				UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();

				String pivaTitolare = (String) session.getAttribute("piva");

				LinkedList<Luogo> luoghi = utenteDao.findAllLocation(pivaTitolare);

				Luogo luogoN = new Luogo(utenteDao.findByPrimaryKey(pivaTitolare), luogo.getNome(),
						luogo.getProvincia(), luogo.getComune(), luogo.getIndirizzo());

				boolean luogoNuovo = true;

				for (Luogo l : luoghi) {
					if (l.getNome().equals(luogoN.getNome()) && l.getProvincia().equals(luogoN.getProvincia())
							&& l.getComune().equals(luogoN.getComune())
							&& l.getIndirizzo().equals(luogoN.getIndirizzo())) {

						luogoNuovo = false;
					}
				}

				if (luogoNuovo) {
					luogoDao.save(luogoN);
					String jsonLuogoN = new Gson().toJson(luogoN);
						resp.getWriter().write(jsonLuogoN);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
}