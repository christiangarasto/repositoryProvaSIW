package persistence;



import persistence.dao.EventoDao;
import persistence.dao.LuogoDao;
import persistence.dao.TicketDao;
import persistence.dao.UtenteDao;

class PostgresDAOFactory extends DAOFactory {

	private static  DataSource dataSource;
	
	// --------------------------------------------

	static {
		try {
			Class.forName("org.postgresql.Driver");
			dataSource = new DataSource("jdbc:postgresql://packy.db.elephantsql.com:5432/kndusqco", "kndusqco", "iNG1Dly0_rVEmfLFlcRnLVgqePFCVQ7n");
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
	public TicketDao getTicketDAO() {
		return new TicketDaoJDBC(dataSource);
	}

	@Override
	public UtilDao getUtilDAO(){
		return new UtilDao(dataSource);
	}

}
