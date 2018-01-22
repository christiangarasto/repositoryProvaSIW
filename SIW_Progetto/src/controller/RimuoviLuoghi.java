package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Luogo;
import persistence.DatabaseManager;
import persistence.dao.LuogoDao;

public class RimuoviLuoghi extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("POST RIMUOVI LUOGHI");

		StringBuffer jsonReceived = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));

		String line = reader.readLine();
		while (line != null) {
			jsonReceived.append(line);
			line = reader.readLine();
		}

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

		/// Dopo aver filtrato tutti i codici dei luoghi da eliminare...

		LuogoDao ld = DatabaseManager.getInstance().getDaoFactory().getLuogoDAO();

		for (String codiceLuogo : luoghiDaEliminare) {
			System.out.println("Luogo con codice: (" + codiceLuogo + ")");
			Luogo luogo = ld.findByPrimaryKey(codiceLuogo);
			if (luogo != null) {
				ld.delete(luogo);
			} else {
				System.out.println("Luogo null, non posso rimuovere");
			}
		}

		resp.sendRedirect("gestioneluoghi");

		// Chiamo la get della servlet gestione luoghi per visualizzare i luoghi
		// aggiornati
	}
}
