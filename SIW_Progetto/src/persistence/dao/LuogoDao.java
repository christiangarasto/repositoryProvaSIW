package persistence.dao;

import java.util.LinkedList;
import java.util.List;

import model.Evento;
import model.Luogo;

public interface LuogoDao {
	public void save(Luogo luogo); // Create

	public Luogo findByPrimaryKey(String codice); // Retrieve

	public LinkedList<Luogo> findAll();

	public void update(Luogo luogo); // Update

	public void delete(Luogo luogo); // Delete

	public LinkedList<Evento> findAllEvents(String codice);

	public Luogo findByName(String nomeLuogo);

}
