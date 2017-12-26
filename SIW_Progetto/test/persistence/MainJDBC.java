package persistence;


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
//		EventoDao eventoDao = factory.getEventoDAO();
//		LuogoDao luogoDao = factory.getLuogoDAO();

		Utente utente1 = new Utente("00000001","Ciccio s.r.l");
		
		Utente utente2 = new Utente("00000002", "Carletto s.p.a");


		//CREATE
		utenteDao.save(utente1);
		utenteDao.save(utente2);
		
		//RETRIEVE		
		System.out.println("Elenco utenti");
		for(Utente u : utenteDao.findAll()) {
			System.out.println(u.getpIva() + ", " + u.getNome());
		}	
	}
}
