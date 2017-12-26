package persistence;

import persistence.dao.EventoDao;

public class EventoDaoJDBC implements EventoDao {
	private DataSource dataSource;

	public EventoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	

}
