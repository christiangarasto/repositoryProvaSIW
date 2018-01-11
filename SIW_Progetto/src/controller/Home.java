package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Home extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("nome");
		String messaggio;
		
		System.out.println("Username: " + username);
		
		if(username == null) {System.out.println("Username nullo");
			messaggio = "Login";
			req.setAttribute("loggato", false);
		}else {
			messaggio = "Bentornato " + username;
			req.setAttribute("loggato", true);
		}
		
		System.out.println("Messaggio: " + messaggio);
		req.setAttribute("messaggio", messaggio);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("homepage.jsp");
		dispatcher.forward(req, resp);
	}
	
}
