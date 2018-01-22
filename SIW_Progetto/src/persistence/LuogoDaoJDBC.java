package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import model.Evento;
import model.Luogo;
import model.Utente;
import persistence.dao.EventoDao;
import persistence.dao.LuogoDao;
import persistence.dao.UtenteDao;


public class LuogoDaoJDBC implements LuogoDao{
	private DataSource dataSource;
	
	public LuogoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void save(Luogo luogo) {
		Connection connection = this.dataSource.getConnection();
		try {
			Long id = IDBroker.getId(connection);
			luogo.setCodice("lu" + Long.toString(id));
			
		String insert = "insert into luogo(titolare, nome, codice, provincia, comune, indirizzo) values (?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			
			statement.setString(1, luogo.getTitolare().getpIva());
			statement.setString(2, luogo.getNome());
			statement.setString(3, luogo.getCodice());
			statement.setString(4, luogo.getProvincia());
			statement.setString(5, luogo.getComune());
			statement.setString(6, luogo.getIndirizzo());
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
	public Luogo findByPrimaryKey(String codice) {
		Connection connection = this.dataSource.getConnection();
		Luogo luogo = null;
		try {
			PreparedStatement statement;
			String query = "select * from luogo where codice = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, codice);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				luogo = new Luogo();	
					Utente titolare = new Utente();
						titolare.create(result.getString("titolare"));
				
				luogo.setTitolare(titolare);				
				luogo.setNome(result.getString("nome"));	
				luogo.setCodice(result.getString("codice"));	
				luogo.setProvincia(result.getString("provincia"));	
				luogo.setComune(result.getString("comune"));	
				luogo.setIndirizzo(result.getString("indirizzo"));	
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
		return luogo;
	}

	@Override
	public LinkedList<Luogo> findAll() {
		Connection connection = this.dataSource.getConnection();
		LinkedList<Luogo> luoghi= new LinkedList<>();
		try {
			PreparedStatement statement;
			String query = "select * from luogo";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			UtenteDao ud = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
			
			while (result.next()) {
				Luogo luogo = new Luogo();
				Utente titolare = ud.findByPrimaryKey(result.getString("titolare"));
			
				luogo.setTitolare(titolare);				
				luogo.setNome(result.getString("nome"));	
				luogo.setCodice(result.getString("codice"));	
				luogo.setProvincia(result.getString("provincia"));	
				luogo.setComune(result.getString("comune"));	
				luogo.setIndirizzo(result.getString("indirizzo"));	
		
				luoghi.add(luogo);
			}
			return luoghi;
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public void update(Luogo luogo) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update luogo SET titolare = ?, nome = ?, provincia = ?, comune = ?, indirizzo = ? WHERE codice = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, luogo.getTitolare().getpIva());
			statement.setString(2, luogo.getNome());
			statement.setString(3, luogo.getProvincia());
			statement.setString(4, luogo.getComune());
			statement.setString(5, luogo.getIndirizzo());
			statement.setString(6, luogo.getCodice());
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
	public void delete(Luogo luogo) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM luogo WHERE codice = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, luogo.getCodice());
			
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);			
			this.removeForeignKeyFromUtente(luogo, connection);
			//devo eliminare la foreignKey da un evento se faccio la delete di un luogo? cos'è il comando drop cascade?
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

	private void removeForeignKeyFromUtente(Luogo luogo, Connection connection) {
		String update = "update luogo_titolare_fkey SET titolare = NULL WHERE codice = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, luogo.getCodice());
			statement.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();}
}

	@Override
	public LinkedList<Evento> findAllEvents(String codice_luogo) 
	{
		Connection connection = this.dataSource.getConnection();
		EventoDao ed = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
		LinkedList<Evento> eventi = new LinkedList<>();
		try {
			Evento evento;
			PreparedStatement statement;
			String query = "select * from evento where luogo = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, codice_luogo);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				evento = new Evento();
				Luogo luogo = findByPrimaryKey(result.getString("luogo"));
			
				evento.setLuogo(luogo);
				evento.setTitolo(result.getString("titolo"));
				evento.setDescrizione(result.getString("descrizione"));
				evento.setGenere(result.getString("genere"));
				evento.setCodice(result.getString("codice"));
				evento.setData(new java.sql.Date(result.getDate("data").getDate()));
				evento.setOra(new java.sql.Time(result.getTime("ora").getTime()));

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
	public Luogo findByName(String nomeLuogo) {
		Connection connection = this.dataSource.getConnection();
		Luogo luogo = null;
		try {
			PreparedStatement statement;
			String query = "select * from luogo where nome = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, nomeLuogo);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				luogo = new Luogo();	
					Utente titolare = new Utente();
						titolare.create(result.getString("titolare"));
				
				luogo.setTitolare(titolare);				
				luogo.setNome(result.getString("nome"));	
				luogo.setCodice(result.getString("codice"));	
				luogo.setProvincia(result.getString("provincia"));	
				luogo.setComune(result.getString("comune"));	
				luogo.setIndirizzo(result.getString("indirizzo"));	
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
		return luogo;
	}
}
