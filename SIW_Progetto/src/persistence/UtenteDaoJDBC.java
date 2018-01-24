package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Luogo;
import model.Utente;
import persistence.dao.LuogoDao;
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
		String insert = "insert into utente(piva, nome) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, utente.getpIva());
			statement.setString(2, utente.getNome());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				if(connection != null)
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
			
			this.removeForeignKeyFromLuogo(utente, connection);
			
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

	private void removeForeignKeyFromLuogo(Utente utente, Connection connection) {
		LuogoDao ld = DatabaseManager.getInstance().getDaoFactory().getLuogoDAO();
		LinkedList<Luogo> luoghi = ld.findAll();
		for(Luogo l : luoghi) {
			if(l.getTitolare().getpIva().equals(utente.getpIva())) {
				ld.delete(l);
			}
		}
	}

	@Override
	public void setPassword(String piva, String password) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update utente SET password = ? WHERE piva=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, password);
			statement.setString(2, piva);
			statement.executeUpdate();
		} catch (SQLException e) {
 
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public void setEmail(String piva, String email) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update utente SET email = ? WHERE piva=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, email);
			statement.setString(2, piva);
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
	public String findByCredenziali(String email, String password) {
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String query = "select * from utente where email = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
				if(result.getString("password").equals(password)) {
					return result.getString("piva");
					
				}
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
		return null;
	}

	@Override
	public boolean passwordCambiata(String piva, String passwordNuovo) {
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String query = "select * from utente where piva = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, piva);
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
				if(result.getString("password").equals(passwordNuovo)) {
					System.out.println("Password invariata");
					return false;					
				}else {
					return true;
				}
			}
			return false;
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
	public boolean emailCambiata(String piva, String emailNuovo) {
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String query = "select * from utente where piva = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, piva);
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
				if(result.getString("email").equals(emailNuovo)) {
					return false;					
				}else {
					return true;
				}
			}
		return false;
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
	public LinkedList<Luogo> findAllLocation(String pIva) 
	{
		//System.out.println("findAllLocation() is working...");
		
		Connection connection = this.dataSource.getConnection();
		LinkedList<Luogo> luoghi = null;
		try {
			Luogo luogo;
			PreparedStatement statement;
			String query = "select * from luogo where titolare = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, pIva);
			ResultSet result = statement.executeQuery();
			
			UtenteDao ut = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
			Utente utente = ut.findByPrimaryKey(pIva);
			
			if(utente != null)
			{
				luoghi = new LinkedList<>();
				while (result.next()) 
				{
					luogo = new Luogo();
					luogo.setTitolare(utente);
					luogo.setNome(result.getString("nome"));
					luogo.setCodice(result.getString("codice"));
					luogo.setProvincia(result.getString("provincia"));
					luogo.setComune(result.getString("comune"));
					luogo.setIndirizzo(result.getString("indirizzo"));
					luoghi.add(luogo);
				}
			}
			return luoghi;
			
		}catch (SQLException e) { throw new PersistenceException(e.getMessage()); }	 
		finally 
		{
			try { connection.close(); } 
			catch (SQLException e) { throw new PersistenceException(e.getMessage()); }
		}
	}	

}
