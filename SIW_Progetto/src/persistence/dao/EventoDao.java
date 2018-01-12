package persistence.dao;

import java.util.List;

import model.Evento;
import model.Utente;

public interface EventoDao {
	
	public void save(Evento utente);  // Create
	public Evento findByPrimaryKey(String codice);     // Retrieve
	public List<Evento> findAll();
	public void update(Evento evento); //Update
	public void delete(Evento evento); //Delete	
}
