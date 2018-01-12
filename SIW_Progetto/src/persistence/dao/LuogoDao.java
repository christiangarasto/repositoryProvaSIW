package persistence.dao;

import java.util.LinkedList;
import java.util.List;

import model.Evento;
import model.Luogo;

public interface LuogoDao {
	public void save(Luogo luogo);  // Create
	public Luogo findByPrimaryKey(String codice);     // Retrieve
	public List<Luogo> findAll();       
	public void update(Luogo luogo); //Update
	public void delete(Luogo luogo); //Delete	

	public List<Evento> findAllEvents();
	public LinkedList<Luogo> findByTitolare(String pivaTitolare);

}
