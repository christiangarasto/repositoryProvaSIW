package persistence;


import model.Evento;
import model.Luogo;
import model.Utente;
import persistence.dao.EventoDao;
import persistence.dao.LuogoDao;
import persistence.dao.UtenteDao;

public class MainJDBC {
	
	public static void main(String args[]) {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		UtilDao util = factory.getUtilDAO();
		util.dropDatabase();
		
		util.createDatabase();
		
		UtenteDao utenteDao = factory.getUtenteDAO();
			Utente utente1 = new Utente("00000001","Ciccio s.r.l");
			Utente utente2 = new Utente("00000002", "Carletto s.p.a");
			Utente utente3 = new Utente("00000003", "Roma Capitale");

		LuogoDao luogoDao = factory.getLuogoDAO();
			Luogo woodstock = new Luogo(utente1, "Woodstock", "Cosenza", "Corigliano Calabro", "Via le mani dal naso 8");
			Luogo plaza = new Luogo(utente2, "Plaza", "Cosenza", "Rende", "Via esempio1 snc");
			Luogo colosseo = new Luogo(utente3, "Colosseo", "Roma", "Roma", "Piazza del colosseo");
		
		
		EventoDao eventoDao = factory.getEventoDAO();		
			Evento rapinaDay = new Evento("Vi svaligiamo il portafogli", new java.sql.Date(2001,10,02), woodstock);
			Evento rapinaDay2 = new Evento("Vi svaligiamo il portafogli, per la seconda volta", new java.sql.Date(2018,10,02), woodstock);
			Evento capodanno = new Evento("Capodanno 2018 Roma", new java.sql.Date(2017,12,31), colosseo);

		//CREATE
		utenteDao.save(utente1);
		utenteDao.save(utente2);
		utenteDao.save(utente3);
		
		luogoDao.save(woodstock);
		luogoDao.save(colosseo);
		luogoDao.save(plaza);
		
		eventoDao.save(rapinaDay);
		eventoDao.save(rapinaDay2);
		eventoDao.save(capodanno);
		
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
	}
}
