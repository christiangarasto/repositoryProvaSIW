package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Evento;
import model.Luogo;
import model.Utente;
import persistence.dao.LuogoDao;


public class LuogoDaoJDBC implements LuogoDao{
	private DataSource dataSource;
	
	public LuogoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void save(Luogo luogo) {
		Connection connection = this.dataSource.getConnection();
		try {
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
	public List<Luogo> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Luogo> luoghi= new LinkedList<>();
		try {
			Luogo luogo;
			PreparedStatement statement;
			String query = "select * from luogo";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				luogo = new Luogo();	
				Utente titolare = new Utente();
					titolare.create(result.getString("titolare"));
			
				luogo.setTitolare(titolare);				
				luogo.setNome(result.getString("nome"));	
				luogo.setCodice(result.getString("codice"));	
				luogo.setProvincia(result.getString("provincia"));	
				luogo.setComune(result.getString("comune"));	
				luogo.setIndirizzo(result.getString("indirizzo"));	
		
				luoghi.add(luogo);
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
		return luoghi;
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

}
