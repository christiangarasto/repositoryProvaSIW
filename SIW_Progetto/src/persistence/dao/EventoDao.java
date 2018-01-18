package persistence.dao;

import java.util.LinkedList;

import model.Evento;

public interface EventoDao {
	
	public void save(Evento utente);  // Create
	public Evento findByPrimaryKey(String codice);     // Retrieve
	public LinkedList<Evento> findAll();
	public void update(Evento evento); //Update
	public void delete(Evento evento); //Delete	
}
