package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class RimuoviLuoghi extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("POST RIMUOVI LUOGHI");
		
		StringBuffer jsonReceived = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));

		String[] myJsonData = req.getParameterValues("luoghiDaE[]");
		
		for(String s : myJsonData) {
			System.out.println("-- " + s);
		}

		
		/*		
		for(String s : luoghiE)
		{
			System.out.println("- " + s);
		}
		System.out.println(jsonReceived.toString());
	
		JSONObject json = new JSONObject(jsonReceived);
		try {
			System.out.println(":::::::::::: " + json.getString("lu10"));
			
			System.out.println(json.getString("CodiceLuogo"));
			
			
			//!!!Come faccio per prendere tutti i valori dell'array che mi è stato passato? conviene il JSON?!!!
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/	
		//resp.sendRedirect("gestioneluoghi");
	}
}
