package persistence.dao;

import java.util.LinkedList;
import model.Ticket;

public interface TicketDao {
	
	public void save(Ticket ticket);  // Create
	public Ticket findByPrimaryKey(String codice);     // Retrieve
	public LinkedList<Ticket> findAll();
	public void update(Ticket ticket); //Update
	public void delete(Ticket ticket); //Delete
}
