package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Utente;
import persistence.dao.UtenteDao;

public class UtenteDaoJDBC implements UtenteDao{
	private DataSource dataSource;

	public UtenteDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void save(Utente utente) {
		Connection connection = this.dataSource.getConnection();
		try {
		String insert = "insert into utente(pIva, nome) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, utente.getpIva());
			statement.setString(2, utente.getNome());
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
	public Utente findByPrimaryKey(String pIva) {
		Connection connection = this.dataSource.getConnection();
		Utente utente = null;
		try {
			PreparedStatement statement;
			String query = "select * from utente where pIva = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, pIva);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				utente = new Utente();
				utente.setpIva(result.getString("pIva"));				
				utente.setNome(result.getString("nome"));
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
		return utente;
	}

	@Override
	public List<Utente> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Utente> utenti = new LinkedList<>();
		try {
			Utente utente;
			PreparedStatement statement;
			String query = "select * from utente";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				utente = new Utente();
				utente.setpIva(result.getString("pIva"));				
				utente.setNome(result.getString("nome"));
				utenti.add(utente);
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
		return utenti;
	}

	@Override
	public void update(Utente utente) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update utente SET nome = ? WHERE pIva = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, utente.getNome());
			statement.setString(2, utente.getpIva());
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
	public void delete(Utente utente) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM utente WHERE pIva = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, utente.getpIva());
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

}
