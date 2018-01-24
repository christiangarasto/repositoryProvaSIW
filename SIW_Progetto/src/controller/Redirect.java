package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Redirect extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Get redirect");
		
		/*
		HttpSession session = req.getSession();
		
		
		if(session.getAttribute("loggato") == null) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("homepage.jsp");
			dispatcher.forward(req, resp);			
		}
		
		*/
		
		String reindirizza = req.getParameter("reindirizzaA");
		
		
		System.out.println("Richiesta redirect: " + reindirizza);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("eventi.jsp");
		dispatcher.forward(req, resp);	
		
//		resp.sendRedirect("eventi.jsp");
		
		
		
		
	}
	
}
