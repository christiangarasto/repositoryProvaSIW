package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.LinkedList;
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
			String insert = "insert into evento(titolo, descrizione, genere, codice, data, ora, luogo) values (?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, evento.getTitolo());
			statement.setString(2, evento.getDescrizione());
			statement.setString(3, evento.getGenere());
			statement.setString(4, evento.getCodice());
			statement.setDate(5, evento.getData());
			statement.setTime(6, evento.getOra());
			statement.setString(7, evento.getLuogo().getCodice());

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
				evento.setDescrizione(result.getString("titolo"));
				evento.setDescrizione(result.getString("descrizione"));
				evento.setGenere(result.getString("genere"));
				evento.setCodice(result.getString("codice"));
				evento.setData(new java.sql.Date(result.getDate("data").getTime()));	
				evento.setOra(new java.sql.Time(result.getTime("ora").getTime()));
				
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
	public LinkedList<Evento> findAll() {
		Connection connection = this.dataSource.getConnection();
		LinkedList<Evento> eventi = new LinkedList<>();
		
		LuogoDao ld = DatabaseManager.getInstance().getDaoFactory().getLuogoDAO();
		
		try {
			Evento evento;
			PreparedStatement statement;
			String query = "select * from evento";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				evento = new Evento();
				evento.setTitolo(result.getString("titolo"));
				evento.setDescrizione(result.getString("descrizione"));
				evento.setGenere(result.getString("genere"));
				evento.setCodice(result.getString("codice"));
				evento.setData(new java.sql.Date(result.getDate("data").getTime()));
				evento.setOra(new java.sql.Time(result.getTime("ora").getTime()));
				
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
			String update = "update evento SET titolo = ?, descrizione = ?, genere = ?, data = ?, ora = ?, luogo = ? WHERE codice = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, evento.getTitolo());
			statement.setString(2, evento.getDescrizione());
			statement.setString(3, evento.getGenere());
			statement.setString(4, evento.getData().toString());
			statement.setString(5, evento.getOra().toLocaleString());
			statement.setString(6, evento.getLuogo().getCodice());
			statement.setString(7, evento.getCodice());
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

	@Override
	public LinkedList<Evento> eventiDaMostrare(Date dataOdierna) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Evento> eventiOggi(Date dataOdierna) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Evento> eventiPerData(Date data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Evento> eventiPerOra(Time ora) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Evento> eventiPerComune(String comune) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Evento> eventiPerProvincia(String provincia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Evento> eventiPassatiDaUnaSettimana(Date dataOdierna) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Evento> eventiGratuiti() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Evento> eventiAPagamento() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Evento> eventiPerGenere(String genere) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Evento> eventiPerLuogo(String luogo) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
