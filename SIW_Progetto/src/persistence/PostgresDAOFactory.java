package persistence;



import persistence.dao.EventoDao;
import persistence.dao.LuogoDao;
import persistence.dao.UtenteDao;

class PostgresDAOFactory extends DAOFactory {

	
	
	private static  DataSource dataSource;
	

	// --------------------------------------------

	static {
		try {
			Class.forName("org.postgresql.Driver");//.newInstance();
			//questi vanno messi in file di configurazione!!!	
//			dataSource=new DataSource("jdbc:postgresql://52.39.164.176:5432/xx","xx","p@xx");
			dataSource=new DataSource("jdbc:postgresql://localhost:5432/progettoSIW","postgres","postgres");
		} 
		catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n"+e);
			e.printStackTrace();
		}
	}

	
	// --------------------------------------------
	
	@Override
	public UtenteDao getUtenteDAO() {
		return new UtenteDaoJDBC(dataSource);
	}

	@Override
	public LuogoDao getLuogoDAO() {
		return new LuogoDaoJDBC(dataSource);
	}
	
	@Override
	public EventoDao getEventoDAO() {
		return new EventoDaoJDBC(dataSource);
	}

	
	@Override
	public UtilDao getUtilDAO(){
		return new UtilDao(dataSource);
	}
}
