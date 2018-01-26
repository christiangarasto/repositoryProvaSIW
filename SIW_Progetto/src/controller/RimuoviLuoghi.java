package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Luogo;
import persistence.DatabaseManager;
import persistence.dao.LuogoDao;

public class RimuoviLuoghi extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("POST RIMUOVI LUOGHI");

		HttpSession session = req.getSession();

		if (session.getAttribute("loggato") == null) {
			System.out.println("Utente non loggato, non è possibile rimuovere il luogo.");
		} else {

			StringBuffer jsonReceived = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));

			String line = reader.readLine();
			while (line != null) {
				jsonReceived.append(line);
				line = reader.readLine();
			}

			if (jsonReceived.length() > 2) {
				LinkedList<String> luoghiDaEliminare = new LinkedList<String>();

				String[] codici = jsonReceived.toString().split(",");

				for (int i = 0; i < codici.length; i++) {
					if (i == 0) {
						if (codici.length == 1) {
							codici[i] = (String) codici[i].subSequence(2, codici[i].length() - 2);
						} else {
							codici[i] = (String) codici[i].subSequence(2, codici[i].length() - 1);
						}
					} else if (i == codici.length - 1) {
						codici[i] = (String) codici[i].subSequence(1, codici[i].length() - 2);
					} else {
						codici[i] = (String) codici[i].subSequence(1, codici[i].length() - 1);
					}

					System.out.println("aggiungo codice: (" + codici[i] + ")");
					luoghiDaEliminare.add(codici[i]);
				}
				LuogoDao ld = DatabaseManager.getInstance().getDaoFactory().getLuogoDAO();

				boolean sendBack = false;

				for (String codiceLuogo : luoghiDaEliminare) {
					System.out.println("Luogo con codice: (" + codiceLuogo + ")");
					Luogo luogo = ld.findByPrimaryKey(codiceLuogo);
					if (luogo != null) {
						ld.delete(luogo);
						sendBack = true;
					} else {
						System.out.println("Luogo null, non posso rimuovere");
					}
				}

				if (sendBack) {
					String pivaTitolare = (String) session.getAttribute("piva");
					LinkedList<Luogo> luoghiAggiornati = new LinkedList<Luogo>();
					for (Luogo l : ld.findAll()) {
						if (l.getTitolare().getpIva().equals(pivaTitolare)) {
							luoghiAggiornati.add(l);
						}
					}

					String jsonToSend = new Gson().toJson(luoghiAggiornati);
					System.out.println("Restituisco i luoghiAggiornati: " + jsonToSend);
					resp.getWriter().write(jsonToSend);
				}

			}
		}
	}
}
