package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import model.Evento;
import model.Luogo;
import model.Ticket;
import persistence.dao.EventoDao;
import persistence.dao.LuogoDao;
import persistence.dao.TicketDao;

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
				evento.setData(new java.sql.Date(result.getDate("data").getDate()));
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
				evento.setData(new java.sql.Date(result.getDate("data").getDate()));
				evento.setOra(new java.sql.Time(result.getTime("ora").getTime()));

				Luogo l = ld.findByPrimaryKey(result.getString("luogo"));
				evento.setLuogo(l);

				eventi.add(evento);
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
			statement.setString(4, evento.getData().toLocaleString());
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
			// this.removeForeignKeyFromLuogo(evento, connection);

			String delete = "delete FROM evento WHERE codice = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, evento.getCodice());

			// connection.setAutoCommit(false);
			// connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

			this.removeForeignKeyFromTicket(evento, connection);
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
		try {
			String update = "update evento SET luogo = NULL WHERE codice = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, evento.getCodice());
			statement.executeUpdate();
			System.out.println("Evento: Remove fk from luogo");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void removeForeignKeyFromTicket(Evento evento, Connection connection) {
		TicketDao td = DatabaseManager.getInstance().getDaoFactory().getTicketDAO();
		LinkedList<Ticket> ticket = td.findAll();

		for (Ticket t : ticket) {
			if (t.getEvento().getCodice().equals(evento.getCodice()))
				td.delete(t);
		}
	}

	/**********************************************************************************************************/
	@Override
	public LinkedList<Evento> eventiDaMostrare(Date dataOdierna) { // fino a sette giorni prima e futuri
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Evento> eventiOggi(String dataOdierna) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Evento> eventiPerData(String data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Evento> eventiPerOra(String ora) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Evento> eventiPerComune(String comune) {
		EventoDao ed = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();

		LinkedList<Evento> eventiPerComune = new LinkedList<Evento>();
		for (Evento e : ed.findAll()) {
			if (e.getLuogo().getComune().toLowerCase().equals(comune.toLowerCase())) {
				eventiPerComune.add(e);
			}
		}

		return eventiPerComune;
	}

	@Override
	public LinkedList<Evento> eventiPerProvincia(String provincia) {
		EventoDao ed = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();

		LinkedList<Evento> eventiPerProvincia = new LinkedList<Evento>();
		for (Evento e : ed.findAll()) {
			if (e.getLuogo().getProvincia().toLowerCase().equals(provincia.toLowerCase())) {
				eventiPerProvincia.add(e);
			}
		}

		return eventiPerProvincia;
	}

	@Override
	public LinkedList<Evento> eventiPassatiDaUnaSettimana(Date dataOdierna) { ///////////////////////////////////////////////// ?
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Evento> eventiGratuiti() {
		EventoDao ed = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
		LinkedList<Evento> eventiGratuiti = new LinkedList<Evento>();

		TicketDao td = DatabaseManager.getInstance().getDaoFactory().getTicketDAO();

		for (Evento e : ed.findAll()) {
			boolean gratuito = true;
			for (Ticket t : td.findAll()) {
				if (t.getEvento().getCodice().equals(e.getCodice())) {
					gratuito = false;
				}
			}
			if (gratuito) {
				eventiGratuiti.add(e);
			}
		}

		return eventiGratuiti;
	}

	@Override
	public LinkedList<Evento> eventiAPagamento() {
		EventoDao ed = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
		LinkedList<Evento> eventiPagamento = new LinkedList<Evento>();

		TicketDao td = DatabaseManager.getInstance().getDaoFactory().getTicketDAO();

		for (Evento e : ed.findAll()) {
			for (Ticket t : td.findAll()) {
				if (t.getEvento().getCodice().equals(e.getCodice())) {
					if (!eventiPagamento.contains(e))
						eventiPagamento.add(e);
				}
			}
		}

		return eventiPagamento;
	}

	@Override
	public LinkedList<Evento> eventiPerGenere(String genere) {
		Connection connection = this.dataSource.getConnection();
		LinkedList<Evento> eventi = new LinkedList<Evento>();
		LuogoDao ld = DatabaseManager.getInstance().getDaoFactory().getLuogoDAO();

		try {
			String query = "select * FROM evento WHERE genere = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, genere);

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Evento evento = new Evento();
				evento.setTitolo(result.getString("titolo"));
				evento.setDescrizione(result.getString("descrizione"));
				evento.setGenere(result.getString("genere"));
				evento.setCodice(result.getString("codice"));
				evento.setData(new java.sql.Date(result.getDate("data").getDate()));
				evento.setOra(new java.sql.Time(result.getTime("ora").getTime()));

				Luogo l = ld.findByPrimaryKey(result.getString("luogo"));
				evento.setLuogo(l);

				eventi.add(evento);
			}

			return eventi;
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
	public LinkedList<Evento> eventiPerLuogo(String codluogo) {
		Connection connection = this.dataSource.getConnection();
		LinkedList<Evento> eventi = new LinkedList<Evento>();
		LuogoDao ld = DatabaseManager.getInstance().getDaoFactory().getLuogoDAO();

		// Luogo luogo = ld.findByName(nomeLuogo);

		try {
			String query = "select * FROM evento WHERE luogo = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, codluogo);

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Evento evento = new Evento();
				evento.setTitolo(result.getString("titolo"));
				evento.setDescrizione(result.getString("descrizione"));
				evento.setGenere(result.getString("genere"));
				evento.setCodice(result.getString("codice"));
				evento.setData(new java.sql.Date(result.getDate("data").getDate()));
				evento.setOra(new java.sql.Time(result.getTime("ora").getTime()));

				Luogo l = ld.findByPrimaryKey(result.getString("luogo"));
				evento.setLuogo(l);

				eventi.add(evento);
			}

			return eventi;
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
	public LinkedList<Evento> eventiPerLuogo(String nomeLuogo, boolean b) {
		Connection connection = this.dataSource.getConnection();
		LinkedList<Evento> eventi = new LinkedList<Evento>();
		LuogoDao ld = DatabaseManager.getInstance().getDaoFactory().getLuogoDAO();

		Luogo luogo = ld.findByName(nomeLuogo);

		if (luogo != null) {

			try {
				String query = "select * FROM evento WHERE luogo = ?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, luogo.getCodice());

				ResultSet result = statement.executeQuery();
				while (result.next()) {
					Evento evento = new Evento();
					evento.setTitolo(result.getString("titolo"));
					evento.setDescrizione(result.getString("descrizione"));
					evento.setGenere(result.getString("genere"));
					evento.setCodice(result.getString("codice"));
					evento.setData(new java.sql.Date(result.getDate("data").getDate()));
					evento.setOra(new java.sql.Time(result.getTime("ora").getTime()));

					Luogo l = ld.findByPrimaryKey(result.getString("luogo"));
					evento.setLuogo(l);

					eventi.add(evento);
				}

				return eventi;
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
		return null;
	}

	/**********************************************************************************************************/

}
