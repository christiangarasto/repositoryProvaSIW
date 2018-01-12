package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Evento;
import model.Luogo;
import persistence.dao.EventoDao;
import persistence.dao.LuogoDao;

public class EventoDaoJDBC implements EventoDao {
	private DataSource dataSource;

	public EventoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void save(Evento evento) {
		Connection connection = this.dataSource.getConnection();
		try {
			Long id = IDBroker.getId(connection);
			evento.setCodice("ev" + Long.toString(id));
		String insert = "insert into evento(descrizione, codice, data, luogo) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, evento.getDescrizione());
			statement.setString(2, evento.getCodice());
			statement.setDate(3, evento.getData());
			statement.setString(4, evento.getLuogo().getCodice());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public Evento findByPrimaryKey(String codice) {
		Connection connection = this.dataSource.getConnection();
		LuogoDao ld = DatabaseManager.getInstance().getDaoFactory().getLuogoDAO();
		Evento evento = null;
		try {
			PreparedStatement statement;
			String query = "select * from evento where codice = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, codice);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				evento = new Evento();				
				evento.setDescrizione(result.getString("descrizione"));				
				evento.setCodice(result.getString("codice"));
					long secs = result.getDate("data").getTime();
					statement.setDate(4, new java.sql.Date(secs));
				evento.setData(new java.sql.Date(secs));	
				Luogo l = ld.findByPrimaryKey(result.getString("luogo"));
				evento.setLuogo(l);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}	
		return evento;
	}

	@Override
	public List<Evento> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Evento> eventi = new LinkedList<>();
		
		LuogoDao ld = DatabaseManager.getInstance().getDaoFactory().getLuogoDAO();
		
		try {
			Evento evento;
			PreparedStatement statement;
			String query = "select * from evento";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				evento = new Evento();
				evento.setDescrizione(result.getString("descrizione"));				
				evento.setCodice(result.getString("codice"));
				
				long secs = result.getDate("data").getTime();
				evento.setData(new java.sql.Date(secs));	
				
				Luogo l = ld.findByPrimaryKey(result.getString("luogo"));
				evento.setLuogo(l);
				
				eventi.add(evento);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return eventi;
	}

	@Override
	public void update(Evento evento) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update evento SET descrizione = ?, data = ?, luogo = ? WHERE codice = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, evento.getDescrizione());
			statement.setString(2, evento.getData().toString());
			statement.setString(3, evento.getLuogo().getCodice());
			statement.setString(4, evento.getCodice());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public void delete(Evento evento) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM evento WHERE codice = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, evento.getCodice());
			
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);			
			this.removeForeignKeyFromLuogo(evento, connection);
			
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	private void removeForeignKeyFromLuogo(Evento evento, Connection connection) {
			String update = "update luogo SET luogo = NULL WHERE codice = ?";
			try {
				PreparedStatement statement = connection.prepareStatement(update);
				statement.setString(1, evento.getCodice());
				statement.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();}
	}

	

}
