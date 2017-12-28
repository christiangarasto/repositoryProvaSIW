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

		LuogoDao luogoDao = factory.getLuogoDAO();
			Luogo woodstock = new Luogo(utente1, "Woodstock", "cod1", "Cosenza", "Corigliano Calabro", "Via le mani dal naso 8");
		
		
		EventoDao eventoDao = factory.getEventoDAO();		
			Evento rapinaDay = new Evento("Vi svaligiamo il portafogli", "ev1", new java.sql.Date(2001,10,02), woodstock);
			Evento rapinaDay2 = new Evento("Vi svaligiamo il portafogli, per la seconda volta", "ev2", new java.sql.Date(2018,10,02), woodstock);

		//CREATE
		utenteDao.save(utente1);
		utenteDao.save(utente2);
		
		luogoDao.save(woodstock);
		
		eventoDao.save(rapinaDay);
		eventoDao.save(rapinaDay2);
		
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
