package persistence;

import model.Evento;
import model.Luogo;
import model.Ticket;
import model.Utente;
import persistence.dao.EventoDao;
import persistence.dao.LuogoDao;
import persistence.dao.TicketDao;
import persistence.dao.UtenteDao;

public class MainJDBC {
	
	public static void main(String args[]) {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		UtilDao util = factory.getUtilDAO();
		util.dropDatabase();
		
		util.createDatabase();
		
		UtenteDao utenteDao = factory.getUtenteDAO();
			Utente utente1 = new Utente("00000000000","Ciccio s.r.l");
			utenteDao.save(utente1);
			utenteDao.setEmail(utente1.getpIva(), "ciccio@azienda.it");
			utenteDao.setPassword(utente1.getpIva(), "ciccio11");
			
			Utente utente2 = new Utente("00000000001", "Carletto s.p.a");
			utenteDao.save(utente2);
			utenteDao.setEmail(utente2.getpIva(), "carletto@azienda.it");
			utenteDao.setPassword(utente2.getpIva(), "carletto1");
			
			Utente utente3 = new Utente("00000000002", "Giuseppe eventi s.r.l");
			utenteDao.save(utente3);
			utenteDao.setEmail(utente3.getpIva(), "giuseppe@azienda.it");
			utenteDao.setPassword(utente3.getpIva(), "giuseppe1");
			
			Utente utente4 = new Utente("12345678910", "Admin");
			utenteDao.save(utente4);
			utenteDao.setEmail(utente4.getpIva(), "admin@yahoo.it");
			utenteDao.setPassword(utente4.getpIva(), "password1");
	
	
		LuogoDao luogoDao = factory.getLuogoDAO();
			Luogo l1 = new Luogo(utente1, "Vanilla", "CS", "Rende", "Via Niccolo' da Conti");
			luogoDao.save(l1);
		
			Luogo l2 = new Luogo(utente1, "Plaza", "CS", "Rende", "Piazza Giacomo Matteotti");
			luogoDao.save(l2);
			
			Luogo l3 = new Luogo(utente4, "Ristorante Movida", "CS", "Corigliano Calabro", "Viale della libertà");
			luogoDao.save(l3);
			
			Luogo l4 = new Luogo(utente4, "Birreria Crazy Horse", "VV", "Pizzo", "Via Riviera Prangi");
			luogoDao.save(l4);
			
			Luogo l5 = new Luogo(utente4, "Vinyl Live Music Club", "CZ", "Lamezia Terme", "Via San Domenico");
			luogoDao.save(l5);
			
		EventoDao eventoDao = factory.getEventoDAO();
			Evento e1 = new Evento("DJ Set", "Serata di musica con ospite speciale Fabrizio Maurizio", "Musicale", new java.sql.Date(31,01,2018), new java.sql.Time(23,00,00), l1);
			eventoDao.save(e1);
			
			Evento e2 = new Evento("San Valentino", "Trascorri una romantica serata presso il nostro ristorante", "Gastronomico", new java.sql.Date(14,02,2018), new java.sql.Time(21,00,00), l3);
			eventoDao.save(e2);
			
			Evento e3 = new Evento("Jazz Music Live", "Dicono di essere dei predestinati", "Musicale", new java.sql.Date(24,01,2018), new java.sql.Time(22,00,00), l5);
			eventoDao.save(e3);
			
		TicketDao ticketDao = factory.getTicketDAO();
			Ticket t1 = new Ticket("25", "", e1);
			Ticket t2 = new Ticket("25", "", e1);
			Ticket t3 = new Ticket("25", "", e1);
			
			ticketDao.save(t1);
			ticketDao.save(t2);
			ticketDao.save(t3);
			
			Ticket t4 = new Ticket("5", "grscrs96s13d005g", e3);
			Ticket t5 = new Ticket("5", "vntpla91e28f537q", e3);
			Ticket t6 = new Ticket("5", "", e3);
	
			ticketDao.save(t4);
			ticketDao.save(t5);
			ticketDao.save(t6);

			//RETRIEVE		
			System.out.println("---- Elenco utenti");
			for(Utente u : utenteDao.findAll()) {
				System.out.println(u.getpIva() + ", " + u.getNome());
			}	
			
			System.out.println("\n\n---- Elenco Luoghi");
			for(Luogo l : luogoDao.findAll()) {
				System.out.println(l.toString());
			}	
			
			System.out.println("\n\n---- Elenco Eventi");
			for(Evento e : eventoDao.findAll()) {
				System.out.println(e.toString());
			}	
			
			System.out.println("\n\n---- Elenco Ticket");
			for(Ticket t : ticketDao.findAll()) {
				System.out.println(t.toString());
			}
	}
}
