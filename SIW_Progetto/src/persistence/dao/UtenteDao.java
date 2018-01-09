package persistence.dao;

import java.util.List;

import model.Utente;

public interface UtenteDao {
	public void save(Utente utente);  // Create
	public Utente findByPrimaryKey(String pIva);     // Retrieve
	public List<Utente> findAll();       
	public void update(Utente utente); //Update
	public void delete(Utente utente); //Delete	
	public void setPassword(Utente u, String password);
	public void setEmail(Utente u, String email);
	public String findByCredenziali(String nome, String password);
}
