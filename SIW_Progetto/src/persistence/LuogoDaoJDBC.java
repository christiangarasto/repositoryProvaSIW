package persistence;

import persistence.dao.LuogoDao;

public class LuogoDaoJDBC implements LuogoDao{
	private DataSource dataSource;
	
	public LuogoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
