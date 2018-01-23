package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Evento;
import model.Ticket;
import model.Utente;
import persistence.dao.EventoDao;
import persistence.dao.TicketDao;

class TicketDaoJDBC implements TicketDao {
	private DataSource dataSource;
	
	public TicketDaoJDBC(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	public void save(Ticket ticket){
		Connection connection = this.dataSource.getConnection();
		try {
			Long id = IDBroker.getId(connection);
			ticket.setCodice("tk" + Long.toString(id));
			
			String insert = "insert into ticket(codice, prezzo, intestatario, evento) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			
			statement.setString(1, ticket.getCodice());
			statement.setString(2, ticket.getPrezzo());
			statement.setString(3, ticket.getIntestatario());
			statement.setString(4, ticket.getEvento().getCodice());
			
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
	
	public Ticket findByPrimaryKey(String codice) 
	{
		Connection connection = this.dataSource.getConnection();
		Ticket ticket = null;
		try {
			PreparedStatement statement;
			String query = "select * from ticket where codice = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, codice);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				ticket = new Ticket();
				ticket.setCodice(result.getString("codice"));
				ticket.setPrezzo(result.getString("prezzo"));
				ticket.setIntestatario(result.getString("intestatario"));
				
				EventoDao ed = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
				Evento e = ed.findByPrimaryKey(result.getString("evento"));
				
				ticket.setEvento(e);
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
		return ticket;
	}
	
	public LinkedList<Ticket> findAll(){
		
		Connection connection = this.dataSource.getConnection();
		LinkedList<Ticket> tickets = new LinkedList<>();
		
		try {
			Ticket ticket;
			PreparedStatement statement;
			String query = "select * from ticket";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				ticket = new Ticket();
				ticket.setCodice(result.getString("codice"));
				ticket.setPrezzo(result.getString("prezzo"));
				ticket.setIntestatario(result.getString("intestatario"));
				
				EventoDao ed = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
				Evento e = ed.findByPrimaryKey(result.getString("evento"));
				
				ticket.setEvento(e);
				
				tickets.add(ticket);
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
		return tickets;
	}
	
	public void update(Ticket ticket) 
	{
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update ticket SET codice = ?, prezzo = ?, intestatario = ?, evento = ? WHERE codice = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, ticket.getCodice());
			statement.setString(2, ticket.getPrezzo());
			statement.setString(3, ticket.getIntestatario());
			statement.setString(4, ticket.getEvento().getCodice());
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
	
	public void delete(Ticket ticket) 
	{
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM ticket WHERE codice = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, ticket.getCodice());

			//this.removeForeignKeyFromEvento(ticket, connection);
//			connection.setAutoCommit(false);
//			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			
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

	private void removeForeignKeyFromEvento(Ticket ticket, Connection connection) {
		String update = "update ticket SET evento = NULL WHERE codice = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, ticket.getCodice());
			statement.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();}
	}
}
